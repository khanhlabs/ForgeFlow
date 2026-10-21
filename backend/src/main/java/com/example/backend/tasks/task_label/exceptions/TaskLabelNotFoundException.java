package com.example.backend.tasks.task_label.exceptions;

public class TaskLabelNotFoundException extends RuntimeException{
    public TaskLabelNotFoundException(Long taskId,  Long labelId) {
        super("TaskLabel with id ("+ taskId +", "+ labelId + ") not found");
    }
}
