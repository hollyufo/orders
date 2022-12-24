package com.example.demo;

import com.example.demo.domain.AppUser;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.saveUser(new AppUser(null ,"John", "test@gmail.com", "1234", "USER"));
            userService.saveUser(new AppUser(null ,"Tom", "pakha@gmail.com", "1234", "Suplier"));
        };
    }
}
