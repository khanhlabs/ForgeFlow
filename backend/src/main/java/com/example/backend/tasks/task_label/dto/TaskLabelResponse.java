package com.example.backend.tasks.task_label.dto;

import com.example.backend.tasks.task_label.entities.TaskLabelId;
import lombok.Data;

@Data
public class TaskLabelResponse {

    private TaskLabelId id;
    private Long projectId;
    private Long taskId;
    private Long labelId;
}
