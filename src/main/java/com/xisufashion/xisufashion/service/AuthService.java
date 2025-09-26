package com.xisufashion.xisufashion.service;

import com.xisufashion.xisufashion.dto.request.LoginRequest;
import com.xisufashion.xisufashion.dto.request.UserCreateRequest;
import com.xisufashion.xisufashion.dto.response.AuthResponse;
import com.xisufashion.xisufashion.dto.response.UserResponse;
import com.xisufashion.xisufashion.exception.InvalidDataException;

public interface AuthService {

    AuthResponse login(LoginRequest request) throws InvalidDataException;

    UserResponse register(UserCreateRequest request) throws InvalidDataException;

    AuthResponse refreshToken(String refreshToken) throws InvalidDataException;

    void logout(String refreshToken) throws InvalidDataException;

    boolean introspect(String token);
}
