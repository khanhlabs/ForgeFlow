package com.example.backend.tasks.task_label.exceptions;

public class TaskLabelAlreadyExistsException extends RuntimeException{
    public TaskLabelAlreadyExistsException(Long taskId, Long labelId) {
        super("This task label already exists!");
    }
}
