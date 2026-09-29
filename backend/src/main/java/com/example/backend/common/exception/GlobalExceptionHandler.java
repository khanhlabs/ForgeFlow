package com.example.backend.common.exception;

import com.example.backend.organizations.organization.exceptions.OrganizationNotFoundException;
import com.example.backend.projects.project.exceptions.OrganizationProjectNotFoundException;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.organizations.organization_member.exceptions.InvalidRoleScopeException;
import com.example.backend.organizations.organization_member.exceptions.OrganizationMemberAlreadyExistException;
import com.example.backend.organizations.organization_member.exceptions.OrganizationMemberNotFoundException;
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

    @ExceptionHandler(InvalidRoleScopeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidRoleScopeException(
            InvalidRoleScopeException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationMemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrganizationMemberNotFoundException(
            OrganizationMemberNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProjectNotFoundException(
            ProjectNotFoundException exception
    ){
        return exception.getMessage();
    }

    @ExceptionHandler(OrganizationProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrganizationProjectNotFoundException(
            OrganizationProjectNotFoundException exception
    ){
        return exception.getMessage();
    }
}
