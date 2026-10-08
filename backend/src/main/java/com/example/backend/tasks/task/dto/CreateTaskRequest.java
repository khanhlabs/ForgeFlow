package com.example.backend.tasks.task.dto;

import com.example.backend.tasks.task.enums.TaskPriority;
import com.example.backend.tasks.task.enums.TaskStatus;
import com.example.backend.tasks.task.enums.TaskType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank
    @Size(min = 1, max = 255)
    private String title;

    private String description;

    @NotNull
    private TaskStatus status;

    @NotNull
    private TaskPriority priority;

    @NotNull
    private TaskType type;



}
