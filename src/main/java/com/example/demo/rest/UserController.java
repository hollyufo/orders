package com.example.demo.rest;

import com.example.demo.domain.AppUser;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/Register")
    public AppUser register(@RequestBody AppUser appUser) {
        return userService.saveUser(appUser);
    }




    @GetMapping("/")
    public List<AppUser> getAllUsers() {
        return userService.findAll();
    }
    @GetMapping("/add")
    public AppUser addUser(AppUser user) {
        return userService.saveUser(user);
    }
    @GetMapping("/find/{id}")
    public AppUser getUserById(Long id) {
        return userService.findById(id);
    }
    @GetMapping("/delete/{id}")
    public void deleteUserById(Long id) {
        userService.deleteById(id);
    }



}
