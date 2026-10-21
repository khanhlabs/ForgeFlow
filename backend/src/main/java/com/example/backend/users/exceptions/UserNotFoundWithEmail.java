package com.example.backend.users.exceptions;

public class UserNotFoundWithEmail extends RuntimeException{
    public UserNotFoundWithEmail(String email) {
        super("User with email " + email + " not found");
    }
}
