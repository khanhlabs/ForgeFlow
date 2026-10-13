package com.example.backend.tasks.task_comment.services;

import com.example.backend.tasks.task_comment.repositories.TaskCommentRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;

    public TaskCommentService(
            TaskCommentRepository taskCommentRepository
    ) {
        this.taskCommentRepository = taskCommentRepository;
    }

    //CREATE TASK COMMENT
}
