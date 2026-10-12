package com.example.backend.tasks.task.dto;

import com.example.backend.tasks.task.enums.TaskPriority;
import com.example.backend.tasks.task.enums.TaskStatus;
import com.example.backend.tasks.task.enums.TaskType;
import lombok.Data;

@Data
public class UpdateTaskRequest {

    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    private TaskType type;

    private Long sprintId;

    private Long assigneeId;
}
