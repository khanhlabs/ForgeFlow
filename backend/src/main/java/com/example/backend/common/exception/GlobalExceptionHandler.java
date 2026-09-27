package com.example.backend.common.exception;

import com.example.backend.organization.exceptions.OrganizationNotFoundException;
import com.example.backend.organization_members.exceptions.OrganizationMemberAlreadyExistException;
import com.example.backend.roles.exceptions.RoleNotFoundException;
import com.example.backend.users.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrganizationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrganizationNotFoundException(
            OrganizationNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(
            UserNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRoleNotFoundException(
            RoleNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationMemberAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleOrganizationMemberAlreadyExist(
            OrganizationMemberAlreadyExistException exception
    ){
        return exception.getMessage();
    }
}
