package com.example.backend.sprints.exceptions;

public class NotEmptySprintException extends RuntimeException{
    public NotEmptySprintException(Long sprintId) {
        super("This sprint "+sprintId+" still has task");
    }
}
