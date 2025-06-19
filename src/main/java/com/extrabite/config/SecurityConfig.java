package com.extrabite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration.
 * Ensures only JWT-based authentication (no session cookies or form login).
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF since we are using JWTs not sessions
                .csrf(csrf -> csrf.disable())

                // Disable Session Management and Form Login
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())    // disable logout endpoint too
                .httpBasic(httpBasic -> httpBasic.disable()) // disables default security dialog

                // Allow public access to login and register
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login", "/api/auth/register")
                        .permitAll()
                        .requestMatchers("/api/admin/**")
                        .hasAuthority("SUPER_ADMIN")
                        .anyRequest()
                        .authenticated()
                );

        return http.build();
    }
}