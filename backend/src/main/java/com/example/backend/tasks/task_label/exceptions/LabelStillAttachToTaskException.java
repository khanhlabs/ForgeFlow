package com.example.backend.tasks.task_label.exceptions;

import com.example.backend.tasks.task_label.entities.TaskLabelId;

public class LabelStillAttachToTaskException extends RuntimeException{
    public LabelStillAttachToTaskException(TaskLabelId taskLabelId) {
        super("Task " + taskLabelId + " is still attached to task");
    }
}
