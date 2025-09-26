package com.xisufashion.xisufashion.controller;

import com.xisufashion.xisufashion.dto.request.LoginRequest;
import com.xisufashion.xisufashion.dto.request.UserCreateRequest;
import com.xisufashion.xisufashion.dto.response.AuthResponse;
import com.xisufashion.xisufashion.dto.response.UserResponse;
import com.xisufashion.xisufashion.exception.InvalidDataException;
import com.xisufashion.xisufashion.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) throws InvalidDataException {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserCreateRequest request) throws InvalidDataException {
        UserResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestHeader("Authorization") String authHeader) throws InvalidDataException {
        log.info("Refresh token request received");
        String refreshToken = extractTokenFromHeader(authHeader);
        AuthResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestHeader("Authorization") String authHeader) throws InvalidDataException {
        String refreshToken = extractTokenFromHeader(authHeader);
        authService.logout(refreshToken);
        return ResponseEntity.ok(Map.of("message", "Đăng xuất thành công"));
    }

    @PostMapping("/introspect")
    public ResponseEntity<Map<String, Boolean>> introspect(@RequestHeader("Authorization") String authHeader) throws InvalidDataException {
        String accessToken = extractTokenFromHeader(authHeader);
        boolean valid = authService.introspect(accessToken);
        return ResponseEntity.ok(Map.of("valid", valid));
    }

    private String extractTokenFromHeader(String authHeader) throws InvalidDataException {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidDataException("Authorization header must be 'Bearer <token>'");
        }
        return authHeader.substring(7);
    }
}
