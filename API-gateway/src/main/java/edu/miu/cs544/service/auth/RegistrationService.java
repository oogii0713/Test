package edu.miu.cs544.service.auth;

import edu.miu.cs544.domain.ERole;
import edu.miu.cs544.exception.EmailAlreadyExistException;
import edu.miu.cs544.service.auth.request.UserRequest;
import edu.miu.cs544.service.auth.response.UserResponse;

public interface RegistrationService {
    UserResponse saveUser(UserRequest userRequest, ERole roleType) throws EmailAlreadyExistException;
}
