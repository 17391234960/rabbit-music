package com.skyblue.rabbitmusic.repository;

import com.skyblue.rabbitmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
