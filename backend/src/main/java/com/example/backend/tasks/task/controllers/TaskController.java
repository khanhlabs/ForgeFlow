package com.example.backend.tasks.task.controllers;

import com.example.backend.tasks.task.dto.CreateTaskRequest;
import com.example.backend.tasks.task.dto.TaskResponse;
import com.example.backend.tasks.task.dto.UpdateTaskRequest;
import com.example.backend.tasks.task.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //GET ALL TASK
    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    //GET ALL TASK BY SPRINT
    @GetMapping("/sprints/{sprintId}")
    public List<TaskResponse> getTasksBySprint(
            @PathVariable Long sprintId
    ){
        return taskService.getTaskBySprint(sprintId);
    }

    //GET ALL TASK BY PROJECT
    @GetMapping("/projects/{projectId}")
    public List<TaskResponse> getTasksByProject(
            @PathVariable Long projectId
    ){
        return taskService.getTaskByProject(projectId);
    }

    //GET TASK BY ID
    @GetMapping("/{taskId}")
    public TaskResponse getTaskById(
            @PathVariable Long taskId
    ){
        return taskService.getTaskById(taskId);
    }

    //UPDATE TASK
    @PutMapping("/{taskId}")
    public TaskResponse updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody UpdateTaskRequest request
    ) {
        return taskService.updateTask(request, taskId);
    }

    //DELETE TASK
    @DeleteMapping("/{taskId}")
    public void deleteTask(
            @PathVariable Long taskId
    ){
        taskService.deleteTask(taskId);
    }
}
