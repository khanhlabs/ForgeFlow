package com.example.backend.roles.exceptions;

public class RoleInMemberException extends RuntimeException{
    public RoleInMemberException(String message){
        super(message);
    }
}
