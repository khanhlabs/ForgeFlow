package com.example.backend.projects.project_members.exceptions;

public class MemberNotFoundInProjectException extends RuntimeException{
    public MemberNotFoundInProjectException(Long projectId, Long userId) {
        super("Could not find member " + userId + "in project " + projectId);
    }
}
