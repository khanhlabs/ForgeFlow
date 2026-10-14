package com.example.backend.tasks.task_comment.services;

import com.example.backend.tasks.task.entities.Task;
import com.example.backend.tasks.task.exceptions.TaskNotFoundException;
import com.example.backend.tasks.task.repositories.TaskRepository;
import com.example.backend.tasks.task_comment.dto.CreateTaskCommentRequest;
import com.example.backend.tasks.task_comment.dto.TaskCommentResponse;
import com.example.backend.tasks.task_comment.entities.TaskComment;
import com.example.backend.tasks.task_comment.repositories.TaskCommentRepository;
import com.example.backend.users.entities.User;
import com.example.backend.users.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;

    public TaskCommentService(
            TaskCommentRepository taskCommentRepository,
            TaskRepository taskRepository
    ) {
        this.taskCommentRepository = taskCommentRepository;
        this.taskRepository = taskRepository;
    }

    //CREATE TASK COMMENT
    public TaskCommentResponse createTaskComment(CreateTaskCommentRequest request){
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));

        User user = taskRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()))

        TaskComment taskComment = new TaskComment();

        taskComment.setTask(task);
        taskComment.setUser(user);
        taskComment.setContent(request.getContent());

        taskCommentRepository.save(taskComment);

        return toResponse(taskComment);
    }

    //GET ALL TASK COMMENT
    

    //TO RESPONSE
    public TaskCommentResponse toResponse(TaskComment taskComment){
        TaskCommentResponse response = new TaskCommentResponse();

        response.setTaskId(taskComment.getId());
        response.setUserId(taskComment.getUser().getId());
        response.setContent(taskComment.getContent());

        return  response;
    }

}
