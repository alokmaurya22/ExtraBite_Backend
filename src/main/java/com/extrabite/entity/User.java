package com.extrabite.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    // Location can be pin code, city, etc.
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Time of registration, set automatically
    private LocalDateTime registrationDate;

    // By default, true; can be toggled false by user to deactivate account
    private Boolean profileActive;
}