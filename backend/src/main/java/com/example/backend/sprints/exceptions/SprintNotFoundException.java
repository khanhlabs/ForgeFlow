package com.example.backend.sprints.exceptions;

public class SprintNotFoundException extends RuntimeException{
    public SprintNotFoundException(Long sprintId) {
        super("Sprint not found with id " + sprintId);
    }
}
