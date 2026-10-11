package com.example.backend.tasks.task.dto;

import com.example.backend.tasks.task.enums.TaskPriority;
import com.example.backend.tasks.task.enums.TaskStatus;
import com.example.backend.tasks.task.enums.TaskType;
import lombok.Data;

@Data
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    private TaskType type;

    private Long projectId;

    private Long sprintId;

    private Long createdBy;

    private Long assigneeId;

    private Long parentId;
}
