package com.example.backend.organizations.organization.exceptions;

public class OrganizationAlreadyExistException extends RuntimeException
{
    public OrganizationAlreadyExistException(String name)
    {
        super("Organization with name " + name + " already exists");
    }
}
