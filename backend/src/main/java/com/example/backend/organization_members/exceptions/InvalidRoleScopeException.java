package com.example.backend.organization_members.exceptions;

public class InvalidRoleScopeException extends RuntimeException
{
    public InvalidRoleScopeException(long roleId)
    {
        super("Role " + roleId + " is not an organization role.");
    }
}
