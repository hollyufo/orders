package com.example.demo.config;


import com.example.demo.domain.AppUser;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class JWTGenerator {
    private final UserRepository userRepository;

    public JWTGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generatetoken(Authentication authentication){
        String username = authentication.getName();
        // seeting the date for the token to expire in 5days
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 5 * 24 * 60 * 60 * 1000);
        Date currentDate = new Date();
        // getting user roles
        Optional<AppUser> user = userRepository.findByEmail(username);
        String roles = user.get().getRoles().stream().map(role -> role.getName()).collect(Collectors.joining(","));

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .claim("roles", roles)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SECRET)
                .compact();
        return token;
    }
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstant.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstant.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");

        }
    }
}
