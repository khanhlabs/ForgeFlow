package com.example.backend.projects.project.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long projectId){
        super("Could not find project with id " + projectId);
    }
}
