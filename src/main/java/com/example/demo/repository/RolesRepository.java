package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
