package com.example.backend.tasks.task_label.controllers;

import com.example.backend.tasks.task_label.dto.CreateTaskLabelRequest;
import com.example.backend.tasks.task_label.dto.TaskLabelResponse;
import com.example.backend.tasks.task_label.services.TaskLabelService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/labels")
public class TaskLabelController {

    private final TaskLabelService taskLabelService;

    public TaskLabelController(
            TaskLabelService taskLabelService
    ) {
        this.taskLabelService = taskLabelService;
    }

    @PostMapping
    public TaskLabelResponse createTaskLabel(
            @Valid @RequestBody CreateTaskLabelRequest request
    ){
        return taskLabelService.createTaskLabel(request);
    }
}
