package com.example.backend.tasks.task_label.controllers;

import com.example.backend.tasks.task_label.dto.CreateTaskLabelRequest;
import com.example.backend.tasks.task_label.dto.TaskLabelResponse;
import com.example.backend.tasks.task_label.dto.UpdateTaskLabelRequest;
import com.example.backend.tasks.task_label.services.TaskLabelService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-labels")
public class TaskLabelController {

    private final TaskLabelService taskLabelService;

    public TaskLabelController(
            TaskLabelService taskLabelService
    ) {
        this.taskLabelService = taskLabelService;
    }

    //CREATE TASK LABEL
    @PostMapping
    public TaskLabelResponse createTaskLabel(
            @Valid @RequestBody CreateTaskLabelRequest request
    ){
        return taskLabelService.createTaskLabel(request);
    }

    //GET ALL TASK LABEL
    @GetMapping
    public List<TaskLabelResponse> getAllTaskLabels() {
        return taskLabelService.getAllTaskLabels();
    }

    //GET TASK LABEL BY TASK
    @GetMapping("/tasks/{taskId}")
    public List<TaskLabelResponse> getTaskLabelsByTask(
            @PathVariable Long taskId
    ){
        return taskLabelService.getAllTaskLabelByTask(taskId);
    }

    //UPDATE TASK LABEL
    @PutMapping("/tasks/{taskId}/labels/{labelId}/projects/{projectId}")
    public TaskLabelResponse updateTaskLabel(
            @PathVariable Long taskId,
            @PathVariable Long labelId,
            @PathVariable Long projectId,
            @Valid @RequestBody UpdateTaskLabelRequest request
    ){
        return taskLabelService.updateTaskLabel(taskId, labelId, projectId, request);
    }

    //DELETE TASK LABEL
    @DeleteMapping("/tasks/{taskId}/labels/{labelId}")
    public void deleteTaskLabel(
            @PathVariable Long taskId,
            @PathVariable Long labelId
    ){
        taskLabelService.deleteTaskLabel(taskId, labelId);
    }
}
