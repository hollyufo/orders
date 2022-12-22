package com.example.demo.rest;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    @GetMapping("/add")
    public User addUser(User user) {
        return userService.saveUser(user);
    }
    @GetMapping("/find/{id}")
    public User getUserById(Long id) {
        return userService.findById(id);
    }
    @GetMapping("/find/Email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
    @GetMapping("/delete/{id}")
    public void deleteUserById(Long id) {
        userService.deleteById(id);
    }

}
