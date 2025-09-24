package com.xisufashion.xisufashion.repository;

import com.xisufashion.xisufashion.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
