package com.example.backend.tasks.task.exceptions;

public class TaskContainsSubtaskException extends RuntimeException{
    public TaskContainsSubtaskException(Long taskId){
        super("Task " +taskId+ " contains subtask");
    }
}
