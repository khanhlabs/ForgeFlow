package com.example.backend.projects.project.services;

import com.example.backend.organizations.organization.entities.Organization;
import com.example.backend.organizations.organization.exceptions.OrganizationNotFoundException;
import com.example.backend.projects.project.dto.UpdateProjectRequest;
import com.example.backend.projects.project.exceptions.OrganizationProjectNotFoundException;
import com.example.backend.projects.project.exceptions.ProjectHasSprintException;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.organizations.organization.repositories.OrganizationRepository;
import com.example.backend.projects.project.dto.CreateProjectRequest;
import com.example.backend.projects.project.dto.ProjectResponse;
import com.example.backend.projects.project.entities.Project;
import com.example.backend.projects.project.repositories.ProjectRepository;
import com.example.backend.projects.project.exceptions.ProjectHasMemberException;
import com.example.backend.projects.project_members.repositories.ProjectMemberRepository;
import com.example.backend.sprints.repositories.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final SprintRepository sprintRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            OrganizationRepository organizationRepository,
            ProjectMemberRepository projectMemberRepository,
            SprintRepository sprintRepository
            ) {
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.sprintRepository = sprintRepository;
    }

    //CREATE PROJECT
    public ProjectResponse createProject(
            CreateProjectRequest request
    ) {
        Organization organization = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new OrganizationNotFoundException(request.getOrganizationId()));

        Project project = new Project();

        project.setName(request.getName());
        project.setOrganization(organization);

        projectRepository.save(project);

        return toResponse(project);
    }

    //GET PROJECT
    public ProjectResponse getProject(
            Long projectId
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        return toResponse(project);
    }

    //GET ALL PROJECT
    public List<ProjectResponse> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET ALL PROJECT BY ORGANIZATION ID
    public List<ProjectResponse> getProjectsByOrganization(Long organizationId) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new OrganizationNotFoundException(organizationId);
        }

        return projectRepository.findAllByOrganizationId(organizationId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //UPDATE PROJECT
    public ProjectResponse updateProject(
            Long organizationId,
            Long projectId,
            UpdateProjectRequest request
    ){
        Project project = projectRepository.findByOrganizationIdAndId(organizationId, projectId)
                .orElseThrow(()-> new OrganizationProjectNotFoundException(organizationId, projectId));

        project.setName(request.getName());

        projectRepository.save(project);

        return toResponse(project);
    }

    //DELETE PROJECT
    public void deleteProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }

        if (projectMemberRepository.existsByProjectId(projectId)){
            throw new ProjectHasMemberException(projectId);
        }

        if (sprintRepository.existsByProjectId(projectId)){
            throw new ProjectHasSprintException(projectId);
        }

        projectRepository.deleteById(projectId);
    }

    //TO RESPONSE
    public ProjectResponse toResponse(
            Project project
    ) {
        ProjectResponse response = new ProjectResponse();

        response.setId(project.getId());
        response.setName(project.getName());
        response.setOrganizationId(project.getOrganization().getId());

        return response;
    }
}
