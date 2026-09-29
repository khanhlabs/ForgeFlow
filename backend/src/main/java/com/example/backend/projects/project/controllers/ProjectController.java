package com.example.backend.projects.project.controllers;

import com.example.backend.projects.project.dto.CreateProjectRequest;
import com.example.backend.projects.project.dto.ProjectResponse;
import com.example.backend.projects.project.dto.UpdateProjectRequest;
import com.example.backend.projects.project.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(
            ProjectService projectService
    ) {
        this.projectService = projectService;
    }

    //CREATE PROJECT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse createProject(
            @Valid @RequestBody CreateProjectRequest request
    ){
        return projectService.createProject(request);
    }

    //GET PROJECT
    @GetMapping("/{projectId}")
    public ProjectResponse getProject(
            @PathVariable Long projectId
    ){
        return projectService.getProject(projectId);
    }

    //GET ALL PROJECTS
    @GetMapping
    public List<ProjectResponse> getProjects(){
        return projectService.getAllProjects();
    }

    //GET ALL PROJECT BY ORGANIZATION
    @GetMapping("/organization/{organizationId}")
    public List<ProjectResponse> getProjectsByOrganizationId(
            @PathVariable Long organizationId
    ){
        return projectService.getProjectsByOrganization(organizationId);
    }

    //UPDATE PROJECT
    @PatchMapping("/{projectId}/organizations/{organizationId}")
    public ProjectResponse updateProject(
            @PathVariable Long organizationId,
            @PathVariable Long projectId,
            @Valid @RequestBody UpdateProjectRequest request
    ){
        return projectService.updateProject(organizationId, projectId, request);
    }

    //DELETE PROJECT
    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

}
