package com.example.backend.tasks.task_comment.dto;

import lombok.Data;

@Data
public class TaskCommentResponse {
    private Long taskId;
    private Long userId;
    private String content;
}
