package com.xisufashion.xisufashion.service;


import com.xisufashion.xisufashion.domain.User;
import com.xisufashion.xisufashion.dto.request.UserCreateRequest;
import com.xisufashion.xisufashion.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    public UserResponse createUser(UserCreateRequest req);

    public UserResponse getUserById(Long id);

    public List<UserResponse> getAllUsers();

    public UserResponse updateUser(Long id, UserCreateRequest req);

    public void deleteUser(Long id);
}
