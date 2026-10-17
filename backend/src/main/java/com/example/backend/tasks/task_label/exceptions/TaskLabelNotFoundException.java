package com.example.backend.tasks.task_label.exceptions;

import com.example.backend.tasks.task_label.entities.TaskLabelId;

public class TaskLabelNotFoundException extends RuntimeException{
    public TaskLabelNotFoundException(TaskLabelId taskLabelId) {
        super("TaskLabel with id " + taskLabelId + " not found");
    }
}
