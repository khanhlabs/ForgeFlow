package com.example.backend.tasks.task_comment.exceptions;

public class SameProjectForTaskUserException extends RuntimeException{
    public SameProjectForTaskUserException(String message){
        super(message);
    }
}
