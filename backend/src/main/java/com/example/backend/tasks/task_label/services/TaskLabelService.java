package com.example.backend.tasks.task_label.services;

import com.example.backend.labels.entities.Label;
import com.example.backend.labels.exceptions.LabelNotFoundExceptions;
import com.example.backend.labels.repositories.LabelRepository;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.projects.project.repositories.ProjectRepository;
import com.example.backend.tasks.task.entities.Task;
import com.example.backend.tasks.task.exceptions.TaskNotFoundException;
import com.example.backend.tasks.task.repositories.TaskRepository;
import com.example.backend.tasks.task_label.dto.CreateTaskLabelRequest;
import com.example.backend.tasks.task_label.dto.TaskLabelResponse;
import com.example.backend.tasks.task_label.dto.UpdateTaskLabelRequest;
import com.example.backend.tasks.task_label.entities.TaskLabel;
import com.example.backend.tasks.task_label.entities.TaskLabelId;
import com.example.backend.tasks.task_label.exceptions.LabelStillAttachToTaskException;
import com.example.backend.tasks.task_label.exceptions.TaskLabelNotFoundException;
import com.example.backend.tasks.task_label.exceptions.TaskLabelProjectException;
import com.example.backend.tasks.task_label.repositories.TaskLabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLabelService {

    private final TaskLabelRepository taskLabelRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final LabelRepository labelRepository;

    public TaskLabelService(
            TaskLabelRepository taskLabelRepository,
            ProjectRepository projectRepository,
            TaskRepository taskRepository,
            LabelRepository labelRepository
    ) {
        this.taskLabelRepository = taskLabelRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.labelRepository = labelRepository;
    }

    //CREATE TASK LABEL
    public TaskLabelResponse createTaskLabel(
            CreateTaskLabelRequest request
    ){
        if (!projectRepository.existsById(request.getProjectId())) {
            throw new ProjectNotFoundException(request.getProjectId());
        }

        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));

        Label label = labelRepository.findById(request.getLabelId())
                .orElseThrow(() -> new LabelNotFoundExceptions(request.getLabelId()));

        if (!task.getProject().getId().equals(label.getProject().getId())){
            throw new TaskLabelProjectException("Task label does not in the same project project");
        }

        TaskLabelId id = new TaskLabelId();
        id.setTaskId(request.getTaskId());
        id.setLabelId(request.getLabelId());

        TaskLabel taskLabel = new TaskLabel();

        taskLabel.setId(id);
        taskLabel.setProjectId(request.getProjectId());
        taskLabel.setTask(task);
        taskLabel.setLabel(label);

        taskLabelRepository.save(taskLabel);

        return toResponse(taskLabel);
    }

    //GET ALL TASK LABEL
    public List<TaskLabelResponse> getAllTaskLabels(){
       return taskLabelRepository.findAll()
               .stream()
               .map(this::toResponse)
               .toList();
    }

    //GET TASK LABEL BY TASK
    public List<TaskLabelResponse> getAllTaskLabelByTask(Long taskId){
        return taskLabelRepository.findByTaskId(taskId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //UPDATE TASK LABEL
    public TaskLabelResponse updateTaskLabel(
            TaskLabelId taskLabelId,
            UpdateTaskLabelRequest request
    ){
        TaskLabel taskLabel = taskLabelRepository.findById(taskLabelId)
                .orElseThrow(() -> new TaskLabelNotFoundException(taskLabelId));

        Task task = taskRepository.findById(request.getTaskId())
                        .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));

        Label label = labelRepository.findById(request.getLabelId())
                        .orElseThrow(() -> new LabelNotFoundExceptions(request.getLabelId()));

        taskLabel.setTask(task);
        taskLabel.setLabel(label);

        taskLabelRepository.save(taskLabel);

        return toResponse(taskLabel);
    }

    //DELETE TASK LABEL
    public void deleteTaskLabel(TaskLabelId taskLabelId){

        if (taskLabelRepository.existsById(taskLabelId)){
            throw new LabelStillAttachToTaskException(taskLabelId);
        }

        taskLabelRepository.deleteById(taskLabelId);
    }


    //TO RESPONSE
    public TaskLabelResponse toResponse(TaskLabel taskLabel){
        TaskLabelResponse response = new TaskLabelResponse();

        response.setId(taskLabel.getId());
        response.setProjectId(taskLabel.getProjectId());
        response.setTaskId(taskLabel.getTask().getId());
        response.setLabelId(taskLabel.getLabel().getId());

        return response;
    }
}
