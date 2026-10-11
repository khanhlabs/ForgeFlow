package com.example.backend.tasks.task.exceptions;

public class SubtaskMismatchException extends RuntimeException{
    public SubtaskMismatchException(Long sprintId, Long parentTaskId) {
        super("Subtask are not in the same sprint " + sprintId + " with parent task " + parentTaskId);
    }
}
