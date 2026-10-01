package com.example.backend.projects.project_members.exceptions;

public class NotEmptyProjectException extends RuntimeException{
    public NotEmptyProjectException(
            Long projectId
    ){
        super("Project " + projectId + " still has members");
    }
}
