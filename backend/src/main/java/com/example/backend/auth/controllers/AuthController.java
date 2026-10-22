package com.example.backend.auth.controllers;

import com.example.backend.auth.services.AuthService;
import com.example.backend.users.dto.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forgeflow/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    //REGISTER
    @PostMapping("/register")
    public void register(
            @Valid @RequestBody CreateUserRequest request
    ){
        authService.register(request);
    }

    //LOGIN

}
