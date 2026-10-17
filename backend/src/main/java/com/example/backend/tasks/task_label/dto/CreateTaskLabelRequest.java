package com.example.backend.tasks.task_label.dto;

import com.example.backend.tasks.task_label.entities.TaskLabelId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskLabelRequest {

    @NotNull
    private Long projectId;
    @NotNull
    private Long taskId;
    @NotNull
    private Long labelId;

}
