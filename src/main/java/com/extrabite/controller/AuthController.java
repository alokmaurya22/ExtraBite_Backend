package com.extrabite.controller;

import com.extrabite.dto.RegisterRequest;
import com.extrabite.dto.RegisterResponse;
import com.extrabite.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle authentication-related endpoints such as register and login.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Register a new user (Donor or Receiver)
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerNewUser(request);
        return ResponseEntity.ok(response);
    }
}