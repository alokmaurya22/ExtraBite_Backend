package com.extrabite.dto;

import com.extrabite.entity.Role;
import lombok.*;

/**
 * DTO to send response after successful registration.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Long id;

    private String fullName;

    private String email;

    private String contactNumber;

    private String location;

    private Role role;

    private String message;
}