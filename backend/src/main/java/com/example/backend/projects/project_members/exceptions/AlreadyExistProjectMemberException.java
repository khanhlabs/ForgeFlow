package com.example.backend.projects.project_members.exceptions;

public class AlreadyExistProjectMemberException extends RuntimeException{
    public AlreadyExistProjectMemberException(
            Long userId,
            Long projectId
    ){
        super("User " + userId + " already exists in project " + projectId);
    }
}
