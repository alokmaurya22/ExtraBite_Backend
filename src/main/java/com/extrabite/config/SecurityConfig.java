package com.extrabite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * This class defines security settings and open endpoints for the application.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF for simplicity (enable in production with JWT)
                .csrf(csrf -> csrf.disable())

                // Authorize requests based on endpoint
                .authorizeHttpRequests(auth -> auth
                        // Allow public access to registration or any future login route
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/admin/**").hasAuthority("SUPER_ADMIN") // Protected: only SUPER_ADMIN allowed
                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )

                // Use default form login (we'll replace this with JWT in login phase)
                .httpBasic(withDefaults());

        return http.build();
    }
}