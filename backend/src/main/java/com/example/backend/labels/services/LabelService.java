package com.example.backend.labels.services;

import com.example.backend.labels.dto.CreateLabelRequest;
import com.example.backend.labels.dto.LabelResponse;
import com.example.backend.labels.dto.UpdateLabelRequest;
import com.example.backend.labels.entities.Label;
import com.example.backend.labels.exceptions.LabelAlreadyExistsException;
import com.example.backend.labels.exceptions.LabelNotFoundExceptions;
import com.example.backend.labels.exceptions.LabelStillAttachExceptions;
import com.example.backend.labels.repositories.LabelRepository;
import com.example.backend.projects.project.entities.Project;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.projects.project.repositories.ProjectRepository;
import com.example.backend.tasks.task.entities.Task;
import com.example.backend.tasks.task.exceptions.TaskNotFoundException;
import com.example.backend.tasks.task.repositories.TaskRepository;
import com.example.backend.tasks.task_label.entities.TaskLabelId;
import com.example.backend.tasks.task_label.repositories.TaskLabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    private final LabelRepository labelRepository;
    private final ProjectRepository projectRepository;
    private final TaskLabelRepository taskLabelRepository;
    private final TaskRepository taskRepository;

    public LabelService(
            LabelRepository labelRepository,
            ProjectRepository projectRepository,
            TaskLabelRepository taskLabelRepository,
            TaskRepository taskRepository
    ) {
        this.labelRepository = labelRepository;
        this.projectRepository = projectRepository;
        this.taskLabelRepository = taskLabelRepository;
        this.taskRepository = taskRepository;
    }

    //CREATE LABEL
    public LabelResponse createLabel(
            CreateLabelRequest request
    ){
        if (labelRepository.existsByNameAndProjectId(request.getName(), request.getProjectId())) {
            throw new LabelAlreadyExistsException(request.getName());
        }

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException(request.getProjectId()));


        Label label = new Label();

        label.setName(request.getName());
        label.setProject(project);

        labelRepository.save(label);

        return toResponse(label);
    }

    //GET ALL LABEL
    public List<LabelResponse> getAllLabels(){
        return labelRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET LABEL BY PROJECT
    public List<LabelResponse> getLabelsByProject(Long projectId){
        return labelRepository.findByProjectId(projectId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //UPDATE LABEL
    public LabelResponse updateLabel(
            Long labelId,
            UpdateLabelRequest request
    ){
        Label label = labelRepository.findById(labelId)
                .orElseThrow(() -> new LabelNotFoundExceptions(labelId));

        if (labelRepository.existsByNameAndProjectId(request.getName(), label.getProject().getId())){
            throw new LabelAlreadyExistsException(request.getName());
        }

        label.setName(request.getName());

        labelRepository.save(label);

        return toResponse(label);
    }

    //DELETE LABEL
    public void deleteLabel(
            Long labelId,
            Long taskId
    ){
        if (taskLabelRepository.existsByTaskIdAndLabelId(taskId, labelId)){
            throw new LabelStillAttachExceptions(labelId, taskId);
        }

        labelRepository.deleteById(labelId);
    }

    //TO RESPONSE
    public LabelResponse toResponse(Label label){
        LabelResponse labelResponse = new LabelResponse();

        labelResponse.setId(label.getId());
        labelResponse.setName(label.getName());
        labelResponse.setProjectId(label.getProject().getId());

        return labelResponse;
    }
}
