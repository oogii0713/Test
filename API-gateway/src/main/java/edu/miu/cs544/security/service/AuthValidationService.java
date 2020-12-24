package edu.miu.cs544.security.service;

import edu.miu.cs544.service.auth.response.UserResponse;

public interface AuthValidationService {
    UserResponse validateToken(String token);
}
