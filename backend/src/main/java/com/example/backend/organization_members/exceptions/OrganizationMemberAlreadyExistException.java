package com.example.backend.organization_members.exceptions;

public class OrganizationMemberAlreadyExistException extends RuntimeException {
    public OrganizationMemberAlreadyExistException(Long organizationId, Long userId)
    {
        super("User with id: " + userId + " already exists in organization with id: " + organizationId);
    }
}
