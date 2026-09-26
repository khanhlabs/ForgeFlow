package com.example.backend.common.exception;

import com.example.backend.organization.exceptions.OrganizationNotFoundException;
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
}
