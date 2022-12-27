package com.example.demo.repository;

import com.example.demo.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    AppUser findByName(String name);
    List<AppUser> findByRole(String role);
}
