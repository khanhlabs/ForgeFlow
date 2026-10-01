package com.example.backend.organizations.organization.exceptions;

public class OrganizationHasMemberException extends RuntimeException{
    public OrganizationHasMemberException(Long organizationId){
        super("Organization " + organizationId + " still has member");
    }
}
