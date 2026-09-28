package com.example.backend.organizations.organization_member.exceptions;

public class OrganizationMemberAlreadyExistException extends RuntimeException {
    public OrganizationMemberAlreadyExistException(Long organizationId, Long userId)
    {
        super("User with id: " + userId + " already exists in organization with id: " + organizationId);
    }
}
