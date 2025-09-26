package com.xisufashion.xisufashion.service.impl;

import com.xisufashion.xisufashion.domain.User;
import com.xisufashion.xisufashion.dto.request.LoginRequest;
import com.xisufashion.xisufashion.dto.request.UserCreateRequest;
import com.xisufashion.xisufashion.dto.response.AuthResponse;
import com.xisufashion.xisufashion.dto.response.UserResponse;
import com.xisufashion.xisufashion.exception.InvalidDataException;
import com.xisufashion.xisufashion.repository.UserRepository;
import com.xisufashion.xisufashion.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Value("${jwt.expiration}")
    private long accessTokenExpiration;

    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) throws InvalidDataException {
        log.info("Login attempt for username: {}", request.getUsername());

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    log.warn("Login failed - user not found: {}", request.getUsername());
                    return new InvalidDataException("Invalid username or password");
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Login failed - invalid password for username: {}", request.getUsername());
            throw new InvalidDataException("Invalid username or password");
        }

        String accessToken = generateAccessToken(user.getUsername());
        String refreshToken = UUID.randomUUID().toString();

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        log.info("Login successful for username: {}", user.getUsername());
        return new AuthResponse(accessToken, refreshToken, user.getUsername(), accessTokenExpiration);
    }

    @Override
    @Transactional
    public UserResponse register(UserCreateRequest request) throws InvalidDataException {
        log.info("Registration attempt for username: {}", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("Registration failed - username already exists: {}", request.getUsername());
            throw new InvalidDataException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = userRepository.save(user);
        log.info("Registration successful for username: {}", savedUser.getUsername());

        return convertToResponse(savedUser);
    }

    @Override
    @Transactional
    public AuthResponse refreshToken(String refreshToken) throws InvalidDataException {
        log.info("Refresh token request received");

        if (!StringUtils.hasText(refreshToken)) {
            log.warn("Refresh token request with empty token");
            throw new InvalidDataException("Refresh token is required");
        }

        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> {
                    log.warn("Invalid refresh token used");
                    return new InvalidDataException("Invalid refresh token");
                });

        log.info("Valid refresh token for user: {}", user.getUsername());

        String newAccessToken = generateAccessToken(user.getUsername());
        String newRefreshToken = UUID.randomUUID().toString();

        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        log.info("Token refresh successful for user: {}", user.getUsername());
        return new AuthResponse(newAccessToken, newRefreshToken, user.getUsername(), accessTokenExpiration);
    }

    @Override
    @Transactional
    public void logout(String refreshToken) throws InvalidDataException {
        log.info("Logout request received");

        if (!StringUtils.hasText(refreshToken)) {
            throw new InvalidDataException("Refresh token is required");
        }

        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> {
                    log.warn("Invalid refresh token used for logout");
                    return new InvalidDataException("Invalid refresh token");
                });

        user.setRefreshToken(null);
        userRepository.save(user);

        log.info("User logged out successfully: {}", user.getUsername());
    }

    @Override
    public boolean introspect(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(signerKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().after(new Date());

        } catch (Exception e) {
            log.debug("Token introspection failed: {}", e.getMessage());
            return false;
        }
    }

    private String generateAccessToken(String username) {
        try {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                    .signWith(SignatureAlgorithm.HS512, signerKey.getBytes())
                    .compact();

        } catch (Exception e) {
            log.error("Failed to generate access token for user: {}", username, e);
            throw new RuntimeException("Token generation failed", e);
        }
    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
