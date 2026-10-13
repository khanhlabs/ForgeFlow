package com.example.backend.tasks.task_comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskCommentRequest {

    @NotNull
    private Long taskId;

    @NotNull
    private Long userId;

    @NotNull
    private String content;
}
