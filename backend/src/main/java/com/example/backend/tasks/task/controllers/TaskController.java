package com.example.backend.tasks.task.controllers;

import com.example.backend.tasks.task.dto.CreateTaskRequest;
import com.example.backend.tasks.task.dto.TaskResponse;
import com.example.backend.tasks.task.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //CREATE TASK
    @PostMapping
    public TaskResponse createTask(
            @Valid @RequestBody CreateTaskRequest request
    ){
        return taskService.createTask(request);
    }
}
