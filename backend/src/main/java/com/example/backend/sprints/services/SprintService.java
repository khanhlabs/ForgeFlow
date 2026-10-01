package com.example.backend.sprints.services;

import com.example.backend.projects.project.entities.Project;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.projects.project.repositories.ProjectRepository;
import com.example.backend.sprints.dto.CreateSprintRequest;
import com.example.backend.sprints.dto.SprintResponse;
import com.example.backend.sprints.dto.UpdateSprintRequest;
import com.example.backend.sprints.entities.Sprint;
import com.example.backend.sprints.exceptions.InvalidSprintName;
import com.example.backend.sprints.exceptions.SprintNotFoundException;
import com.example.backend.sprints.repositories.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;

    public SprintService(
            SprintRepository sprintRepository,
            ProjectRepository projectRepository
    ) {
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
    }

    //CREATE SPRINT
    public SprintResponse createSprint(
            Long projectId,
            CreateSprintRequest request
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()  -> new ProjectNotFoundException(projectId));

        if (sprintRepository.existsByProjectIdAndName(projectId, request.getName())) {
            throw new InvalidSprintName(request.getName());
        }

        Sprint sprint = new Sprint();

        sprint.setName(request.getName());
        sprint.setProject(project);
        sprint.setStartDate(request.getStartDate());
        sprint.setEndDate(request.getEndDate());

        sprintRepository.save(sprint);

        return toResponse(sprint);
    }

    //GET ALL SPRINT
    public List<SprintResponse> getAllSprints() {
        return sprintRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET SPRINT BY ID
    public SprintResponse getSprintById(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(()  -> new SprintNotFoundException(sprintId));

        return toResponse(sprint);
    }

    //UPDATE SPRINT NAME
    public SprintResponse updateSprintName(
            Long projectId,
            Long sprintId,
            UpdateSprintRequest request
    ){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()  -> new ProjectNotFoundException(projectId));

        if (!sprintRepository.existsById(sprintId)) {
            throw new SprintNotFoundException(sprintId);
        }

        if (sprintRepository.existsByProjectIdAndName(projectId, request.getName())) {
            throw new InvalidSprintName(request.getName());
        }

        Sprint sprint = new Sprint();
        sprint.setName(request.getName());
        sprint.setProject(project);

        sprintRepository.save(sprint);

        return toResponse(sprint);
    }

    //DELETE SPRINT
    public void deleteSprint(Long sprintId) {
        if (!sprintRepository.existsById(sprintId)) {
            throw new SprintNotFoundException(sprintId);
        }

        sprintRepository.deleteById(sprintId);
    }

    //TO RESPONSE
    public SprintResponse toResponse(Sprint sprint) {
        SprintResponse response = new SprintResponse();

        response.setId(sprint.getId());
        response.setName(sprint.getName());
        response.setProjectId(sprint.getProject().getId());
        response.setProjectName(sprint.getProject().getName());
        response.setStartDate(sprint.getStartDate());
        response.setEndDate(sprint.getEndDate());

        return response;
    }
}

