package com.example.backend.roles.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long id) {
        super("Role with id " + id + " not found");
    }
}
