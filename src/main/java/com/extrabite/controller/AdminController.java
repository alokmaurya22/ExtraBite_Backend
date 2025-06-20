package com.extrabite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // Sample endpoint for testing
    @GetMapping
    public ResponseEntity<?> adminHome(Authentication authentication) {
        return ResponseEntity.ok("Hello, Super Admin: " + authentication.getName());
    }
    @GetMapping("/{any}")
    public ResponseEntity<?> adminPath(Authentication authentication) {
        return ResponseEntity.ok("Hello, Super Admin: " + authentication.getName());
    }

    // You can extend more admin-specific logic below
}
