package com.extrabite.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Full name of the user
    @Column(nullable = false)
    private String fullName;

    // Email must be unique for login
    @Column(nullable = false, unique = true)
    private String email;

    // Hashed password will be stored (BCrypt)
    @Column(nullable = false)
    private String password;

    // Contact number of the user
    private String contactNumber;

    // Optional: can be city, pin code, area - useful for logistics or filtering
    private String location;

    // Role of the user - donor, receiver, etc.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}