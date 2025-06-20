package com.extrabite.controller;

import com.extrabite.entity.User;
import com.extrabite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    //  GET /api/user/me
    @GetMapping("/me")
    public ResponseEntity<User> getFullUserInfo(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user); // Include everything including password
    }
    @GetMapping("/{any}")
    public ResponseEntity<?> getTokenInfo(@PathVariable String any, Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Return only id and email in JSON
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }

}
