package com.example.backend.organizations.organization_member.exceptions;

public class OrganizationMemberNotFoundException extends RuntimeException
{
    public OrganizationMemberNotFoundException(Long organizationId, Long userId)
    {
        super("User " + userId + " is not an organization member of organization " + organizationId);
    }
}
