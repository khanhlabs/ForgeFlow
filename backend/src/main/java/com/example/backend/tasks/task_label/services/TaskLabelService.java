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
import com.example.backend.tasks.task_label.exceptions.TaskLabelAlreadyExistsException;
import com.example.backend.tasks.task_label.exceptions.TaskLabelNotFoundException;
import com.example.backend.tasks.task_label.exceptions.TaskLabelProjectException;
import com.example.backend.tasks.task_label.repositories.TaskLabelRepository;
import jakarta.transaction.Transactional;
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
    ){
        this.taskLabelRepository = taskLabelRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.labelRepository = labelRepository;
    }

    // CREATE TASK LABEL
    public TaskLabelResponse createTaskLabel(
            CreateTaskLabelRequest request
    ) {
        if (!projectRepository.existsById(request.getProjectId())) {
            throw new ProjectNotFoundException(request.getProjectId());
        }

        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(
                        () -> new TaskNotFoundException(request.getTaskId())
                );

        Label label = labelRepository.findById(request.getLabelId())
                .orElseThrow(
                        () -> new LabelNotFoundExceptions(request.getLabelId())
                );

        Long taskProjectId = task.getProject().getId();
        Long labelProjectId = label.getProject().getId();
        Long requestProjectId = request.getProjectId();

        if (!taskProjectId.equals(labelProjectId)
                || !requestProjectId.equals(taskProjectId)) {
            throw new TaskLabelProjectException(
                    "Task and label must belong to the specified project"
            );
        }

        if (taskLabelRepository.existsByTaskIdAndLabelId(
                request.getTaskId(),
                request.getLabelId()
        )) {
            throw new TaskLabelAlreadyExistsException(
                    request.getTaskId(),
                    request.getLabelId()
            );
        }

        TaskLabelId id = new TaskLabelId();
        id.setTaskId(request.getTaskId());
        id.setLabelId(request.getLabelId());

        TaskLabel taskLabel = new TaskLabel();
        taskLabel.setId(id);
        taskLabel.setProjectId(taskProjectId);
        taskLabel.setTask(task);
        taskLabel.setLabel(label);

        TaskLabel savedTaskLabel = taskLabelRepository.save(taskLabel);

        return toResponse(savedTaskLabel);
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
    @Transactional
    public TaskLabelResponse updateTaskLabel(
            Long taskId,
            Long labelId,
            Long projectId,
            UpdateTaskLabelRequest request
    ) {
        TaskLabel oldTaskLabel = taskLabelRepository
                .findByTaskIdAndLabelId(taskId, labelId)
                .orElseThrow(() ->
                        new TaskLabelNotFoundException(taskId, labelId)
                );

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException(taskId)
                );

        Label newLabel = labelRepository.findById(request.getLabelId())
                .orElseThrow(() ->
                        new LabelNotFoundExceptions(request.getLabelId())
                );

        if (!projectId.equals(task.getProject().getId())) {
            throw new TaskLabelProjectException("Can not change project");
        }

        if (!projectId.equals(newLabel.getProject().getId())) {
            throw new TaskLabelProjectException(
                    "Label must belong to current project"
            );
        }

        if (labelId.equals(newLabel.getId())) {
            return toResponse(oldTaskLabel);
        }

        if (taskLabelRepository.existsByTaskIdAndLabelId(
                taskId,
                newLabel.getId()
        )) {
            throw new TaskLabelAlreadyExistsException(
                    taskId,
                    newLabel.getId()
            );
        }

        taskLabelRepository.delete(oldTaskLabel);
        taskLabelRepository.flush();

        TaskLabelId newId = new TaskLabelId();
        newId.setTaskId(taskId);
        newId.setLabelId(newLabel.getId());

        TaskLabel newTaskLabel = new TaskLabel();
        newTaskLabel.setId(newId);
        newTaskLabel.setProjectId(projectId);
        newTaskLabel.setTask(task);
        newTaskLabel.setLabel(newLabel);

        taskLabelRepository.save(newTaskLabel);

        return toResponse(newTaskLabel);
    }

    //DELETE TASK LABEL
    @Transactional
    public void deleteTaskLabel(
            Long taskId,
            Long labelId
    ){

        if (!taskLabelRepository.existsByTaskIdAndLabelId(taskId, labelId)) {
            throw new TaskLabelNotFoundException(labelId, taskId);
        }

        taskLabelRepository.deleteByTaskIdAndLabelId(taskId, labelId);
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
