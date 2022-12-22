package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }
}
