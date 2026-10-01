package com.example.backend.projects.project_members.exceptions;

public class ProjectMemberNotFoundException extends  RuntimeException{
    public ProjectMemberNotFoundException(Long userId) {
        super("User "+userId+" not found in this project ");
    }
}
