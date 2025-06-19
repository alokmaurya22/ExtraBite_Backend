package com.extrabite.service.impl;

import com.extrabite.dto.RegisterRequest;
import com.extrabite.dto.RegisterResponse;
import com.extrabite.entity.Role;
import com.extrabite.entity.User;
import com.extrabite.repository.UserRepository;
import com.extrabite.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AuthService to handle user registration logic.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterResponse registerNewUser(RegisterRequest request) {

        // Normalize the email to lower case and trim extra spaces
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        // Check if a user with this email already exists
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new RuntimeException("Email already in use");
        }

        // Prevent users from registering as ADMIN or VOLUNTEER through public API
        if (request.getRole() == Role.ADMIN || request.getRole() == Role.VOLUNTEER) {
            throw new RuntimeException("Invalid role for public registration.");
        }

        // Encode password using BCrypt
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create and save user
        User user = User.builder()
                .fullName(request.getFullName())
                .email(normalizedEmail)
                .password(encodedPassword)
                .contactNumber(request.getContactNumber())
                .location(request.getLocation())
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        // Return successful registration response
        return RegisterResponse.builder()
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .contactNumber(savedUser.getContactNumber())
                .location(savedUser.getLocation())
                .role(savedUser.getRole())
                .message("Registration successful")
                .build();
    }
}