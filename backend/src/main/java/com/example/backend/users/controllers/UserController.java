package com.example.backend.users.controllers;

import com.example.backend.users.dto.CreateUserRequest;
import com.example.backend.users.dto.UpdateUserRequest;
import com.example.backend.users.dto.UserResponse;
import com.example.backend.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    //CREATE USER
    @PostMapping
    public UserResponse createUser(
            @Valid @RequestBody CreateUserRequest request
    ){
        return userService.createUser(request);
    }

    //GET ALL USERS
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    //GET USER BY EMAIL
    @GetMapping("/email/{email}")
    public UserResponse getUserByEmail(
            @PathVariable String email
    ){
        return userService.getUserByEmail(email);
    }

    //UPDATE USER
    @PutMapping("/email/{email}")
    public UserResponse updateUser(
            @PathVariable String email,
            @Valid @RequestBody UpdateUserRequest request
    ){
        return userService.updateUser(email, request);
    }

    //DELETE USER
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ){
        userService.deleteUser(userId);
    }
}
