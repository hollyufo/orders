package com.example.demo.rest;


import com.example.demo.config.JWTGenerator;
import com.example.demo.domain.AppUser;
import com.example.demo.domain.Role;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.repository.RolesRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {
        if (userService.existsByEmail(signupDto.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        AppUser user = new AppUser();
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setName(signupDto.getName());

        Role role = rolesRepository.findByName(signupDto.getRolename());
        // casting to list
        user.setRoles(Collections.singletonList(role));

        userService.saveUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDto> signin(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generatetoken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

}
