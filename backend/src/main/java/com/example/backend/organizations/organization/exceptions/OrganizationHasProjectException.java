package com.example.backend.organizations.organization.exceptions;

public class OrganizationHasProjectException extends RuntimeException
{
    public OrganizationHasProjectException(Long organizationId)
    {
        super("Organization " + organizationId + " still has project");
    }
}
