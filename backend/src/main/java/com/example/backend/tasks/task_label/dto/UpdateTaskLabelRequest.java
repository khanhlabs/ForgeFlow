package com.example.backend.tasks.task_label.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTaskLabelRequest {

    @NotNull
    private Long labelId;
}
