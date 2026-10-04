package com.example.backend.projects.project.exceptions;

public class ProjectHasMemberException extends RuntimeException{
    public ProjectHasMemberException(
            Long projectId
    ){
        super("Project " + projectId + " still has members");
    }
}
