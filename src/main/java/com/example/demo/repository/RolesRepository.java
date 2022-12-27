package com.example.demo.repository;

import com.example.demo.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Role> findByName(String name);
}
