package com.example.backend.tasks.task_label.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskLabelRequest {

    @NotNull
    private Long projectId;
    @NotNull
    private Long taskId;

}
