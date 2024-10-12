package com.example.mypenavigatorapi.users.domain.repositories;

import com.example.mypenavigatorapi.users.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
