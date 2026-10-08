package com.example.backend.tasks.task.services;

import com.example.backend.tasks.task.dto.TaskResponse;
import com.example.backend.tasks.task.entities.Task;
import com.example.backend.tasks.task.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(
            TaskRepository taskRepository
    ) {
        this.taskRepository = taskRepository;
    }

    //CREATE TASK
    public TaskResponse createTask(Long sprintId){

    }

    //TO RESPONSE
    public TaskResponse toResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
    }

}
