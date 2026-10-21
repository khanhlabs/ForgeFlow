package com.example.backend.users.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String email) {
        super("Email already exists: " + email);
    }
}
