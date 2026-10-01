package com.example.backend.organizations.organization_member.exceptions;

public class InvalidOrganizationRoleScopeException extends RuntimeException
{
    public InvalidOrganizationRoleScopeException(long roleId)
    {
        super("Role " + roleId + " is not an organization role.");
    }
}
