package com.example.backend.tasks.task.exceptions;

public class TaskSprintMismatchException extends RuntimeException {
    public TaskSprintMismatchException(Long sprintId, Long taskId, Long projectId) {
        super("Task " + taskId + " sprint " + sprintId+ "not found in the same project "+projectId);
    }
}
