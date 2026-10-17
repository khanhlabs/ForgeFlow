package com.example.backend.tasks.task_comment.controllers;

import com.example.backend.tasks.task_comment.dto.CreateTaskCommentRequest;
import com.example.backend.tasks.task_comment.dto.TaskCommentResponse;
import com.example.backend.tasks.task_comment.dto.UpdateTaskCommentRequest;
import com.example.backend.tasks.task_comment.services.TaskCommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task/comments")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    public TaskCommentController(
            TaskCommentService taskCommentService
    ) {
        this.taskCommentService = taskCommentService;
    }

    //CREATE TASK COMMENT
    @PostMapping
    public TaskCommentResponse createTaskComment(
            @Valid @RequestBody CreateTaskCommentRequest request
    ){
        return taskCommentService.createTaskComment(request);
    }

    //GET ALL TASK COMMENT
    @GetMapping
    public List<TaskCommentResponse> getAllTaskComments(){
        return taskCommentService.getAllTaskComments();
    }

    //GET TASK COMMENT BY TASK
    @GetMapping("/tasks/{taskId}")
    public List<TaskCommentResponse> getTaskCommentsByTask(
            @PathVariable Long taskId
    ){
        return taskCommentService.getTaskCommentByTask(taskId);
    }

    //UPDATE TASK COMMENT
    @PatchMapping("/{taskCommentId}")
    public TaskCommentResponse updateTaskComment(
            @PathVariable Long taskCommentId,
            @Valid @RequestBody UpdateTaskCommentRequest request
    ){
        return taskCommentService.updateTaskComment(taskCommentId, request);
    }

    //DELETE TASK COMMENT
    @DeleteMapping("/{taskCommentId}")
    public void deleteTaskComment(
            @PathVariable Long taskCommentId
    ){
        taskCommentService.deleteTaskCommentById(taskCommentId);
    }

}
