package com.example.backend.sprints.exceptions;

public class SprintProjectMismatchException extends RuntimeException{
    public SprintProjectMismatchException(Long  sprintId, Long projectId) {
        super("Sprint " +sprintId+ "not found in project "+projectId);
    }
}
