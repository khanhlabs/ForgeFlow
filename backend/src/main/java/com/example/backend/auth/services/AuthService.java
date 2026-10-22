package com.example.backend.auth.services;

import com.example.backend.users.dto.CreateUserRequest;
import com.example.backend.users.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(
            UserService userService
    ) {
        this.userService = userService;
    }

    //REGISTER
    public void register(CreateUserRequest request) {
        userService.createUser(request);
    }
}
