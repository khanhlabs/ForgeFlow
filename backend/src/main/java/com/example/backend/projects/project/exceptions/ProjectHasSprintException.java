package com.example.backend.projects.project.exceptions;

public class ProjectHasSprintException extends RuntimeException
{
    public ProjectHasSprintException(Long projectId)
    {
        super("Project with id " + projectId + " still has sprint in it");
    }
}
