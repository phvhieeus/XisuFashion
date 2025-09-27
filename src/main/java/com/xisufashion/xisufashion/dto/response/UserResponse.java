package com.xisufashion.xisufashion.dto.response;

import com.xisufashion.xisufashion.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
