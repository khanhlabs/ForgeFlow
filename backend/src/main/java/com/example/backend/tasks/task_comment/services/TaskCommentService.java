package com.example.backend.tasks.task_comment.services;

import com.example.backend.tasks.task.entities.Task;
import com.example.backend.tasks.task.exceptions.TaskNotFoundException;
import com.example.backend.tasks.task.repositories.TaskRepository;
import com.example.backend.tasks.task_comment.dto.CreateTaskCommentRequest;
import com.example.backend.tasks.task_comment.dto.TaskCommentResponse;
import com.example.backend.tasks.task_comment.dto.UpdateTaskCommentRequest;
import com.example.backend.tasks.task_comment.entities.TaskComment;
import com.example.backend.tasks.task_comment.exceptions.TaskCommentNotFoundException;
import com.example.backend.tasks.task_comment.repositories.TaskCommentRepository;
import com.example.backend.users.entities.User;
import com.example.backend.users.exceptions.UserNotFoundException;
import com.example.backend.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskCommentService(
            TaskCommentRepository taskCommentRepository,
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        this.taskCommentRepository = taskCommentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    //CREATE TASK COMMENT
    public TaskCommentResponse createTaskComment(CreateTaskCommentRequest request){
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        TaskComment taskComment = new TaskComment();

        taskComment.setTask(task);
        taskComment.setUser(user);
        taskComment.setContent(request.getContent());

        taskCommentRepository.save(taskComment);

        return toResponse(taskComment);
    }

    //GET ALL TASK COMMENT
    public List<TaskCommentResponse> getAllTaskComments(){
        return taskCommentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET TASK COMMENT BY TASK
    public List<TaskCommentResponse> getTaskCommentByTask(Long taskId){
        return taskCommentRepository.findByTaskId(taskId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET TASK COMMENT BY USER
    public List<TaskCommentResponse> getTaskCommentByUser(Long userId){
        return taskCommentRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET TASK COMMENT BY ID
    public TaskCommentResponse getTaskCommentById(Long taskCommentId){
        TaskComment taskComment = taskCommentRepository.findById(taskCommentId)
                .orElseThrow(() -> new TaskCommentNotFoundException(taskCommentId));

        return toResponse(taskComment);
    }

    //UPDATE TASK COMMENT
    public TaskCommentResponse updateTaskComment(
            Long taskCommentId,
            UpdateTaskCommentRequest request
    ){
        TaskComment taskComment = taskCommentRepository.findById(taskCommentId)
                .orElseThrow(() -> new TaskCommentNotFoundException(taskCommentId));

        taskComment.setContent(request.getContent());

        taskCommentRepository.save(taskComment);
        return toResponse(taskComment);
    }

    //DELETE TASK COMMENT
    public void deleteTaskCommentById(Long taskCommentId){
        if (!taskCommentRepository.existsById(taskCommentId)){
            throw new TaskCommentNotFoundException(taskCommentId);
        }

        taskCommentRepository.deleteById(taskCommentId);
    }

    //TO RESPONSE
    public TaskCommentResponse toResponse(TaskComment taskComment){
        TaskCommentResponse response = new TaskCommentResponse();

        response.setTaskId(taskComment.getId());
        response.setUserId(taskComment.getUser().getId());
        response.setContent(taskComment.getContent());

        return  response;
    }

}
