package com.example.backend.projects.project.exceptions;

public class OrganizationProjectNotFoundException extends RuntimeException{
    public OrganizationProjectNotFoundException(Long organizationId, Long projectId){
        super("Cannot find project with id " + projectId + " in organization with id " + organizationId);
    }
}
