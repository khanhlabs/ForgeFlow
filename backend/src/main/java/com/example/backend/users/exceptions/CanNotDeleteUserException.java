package com.example.backend.users.exceptions;

public class CanNotDeleteUserException extends RuntimeException{
    public CanNotDeleteUserException(String message){
        super(message);
    }
}
