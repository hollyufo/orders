package com.example.demo.service;

import com.example.demo.domain.AppUser;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }
    public UserDetails findByEmail(String email) {
        AppUser user = userRepository.findByEmail(email);
        return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
    }
    public AppUser findById(Long id) {
        return userRepository.findById(id).get();
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
    public AppUser findByName(String name) {
        return userRepository.findByName(name);
    }
    public List<AppUser> findByRole(String role) {
        return userRepository.findByRole(role);
    }
}
