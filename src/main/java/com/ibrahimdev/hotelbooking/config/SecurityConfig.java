package com.ibrahimdev.hotelbooking.config;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
