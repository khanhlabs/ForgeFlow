package com.example.backend.organizations.organization_member.exceptions;

public class InvalidRoleScopeException extends RuntimeException
{
    public InvalidRoleScopeException(long roleId)
    {
        super("Role " + roleId + " is not an organization role.");
    }
}
