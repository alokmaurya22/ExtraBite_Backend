package com.extrabite.service;

import com.extrabite.dto.RegisterRequest;
import com.extrabite.dto.RegisterResponse;

/**
 * Service interface to define authentication operations.
 */
public interface AuthService {

    RegisterResponse registerNewUser(RegisterRequest request);
}