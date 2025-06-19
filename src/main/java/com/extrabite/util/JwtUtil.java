package com.extrabite.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Secret key used to sign the token (keep it long and private in production)
    private final String jwtSecret = "extrabite-super-secure-key-for-jwt-backend";

    // Expiry time in milliseconds (1 day = 24 * 60 * 60 * 1000)
    private final long jwtExpirationMs = 24 * 60 * 60 * 1000L;

    /**
     * Generates JWT token with custom payload
     * @param userId       the user's database ID
     * @param email        the user's email
     * @param role         the user's role string
     * @param profileActive true/false
     * @return signed JWT token
     */
    public String generateToken(Long userId, String email, String role, boolean profileActive) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);
        claims.put("profileActive", profileActive);

        return Jwts.builder()
                .setSubject(email)                          // Will be available as `sub`
                .addClaims(claims)                          // Custom claims
                .setIssuedAt(new Date())                    // iat
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // exp
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}