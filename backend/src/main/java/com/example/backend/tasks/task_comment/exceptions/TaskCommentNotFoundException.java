package com.example.backend.tasks.task_comment.exceptions;

public class TaskCommentNotFoundException extends RuntimeException
{
    public TaskCommentNotFoundException(Long taskCommentId)
    {
        super("TaskComment with id " + taskCommentId + " not found");
    }
}
