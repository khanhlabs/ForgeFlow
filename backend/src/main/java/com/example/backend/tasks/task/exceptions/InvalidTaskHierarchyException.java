package com.example.backend.tasks.task.exceptions;

public class InvalidTaskHierarchyException extends RuntimeException{
    public InvalidTaskHierarchyException(String message) {
        super(message);
    }
}
