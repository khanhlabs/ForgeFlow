package com.example.backend.roles.exceptions;

public class RoleAlreadyExistsException extends RuntimeException
{
    public RoleAlreadyExistsException(String name) {
        super("Role with name " + name + " already exists in this scope.");
    }
}
