package com.example.backend.projects.project_members.exceptions;

public class InvalidProjectRoleScopeException extends RuntimeException{
    public InvalidProjectRoleScopeException(
            Long roleId
    ){
        super("Role with id " + roleId + " is not an project role");
    }
}
