package com.example.demo.rest;

import com.example.demo.config.JwtUtils;
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
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/Register")
    public AppUser register(@RequestBody AppUser appUser) {
        return userService.saveUser(appUser);
    }
    @PostMapping("/login")
    public String login(@RequestBody AppUser appUser) {
         try {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword())
             );
             UserDetails userDetails = userService.findByEmail(appUser.getEmail());
             if (userDetails != null) {
                 response.setHeader("Authorization", jwtUtils.generateToken(userDetails));
             }
         } catch (Exception e) {
             throw new RuntimeException("Invalid username or password");
         }
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
