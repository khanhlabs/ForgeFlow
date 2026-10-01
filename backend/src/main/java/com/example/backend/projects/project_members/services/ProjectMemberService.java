package com.example.backend.projects.project_members.services;

import com.example.backend.organizations.organization_member.exceptions.InvalidOrganizationRoleScopeException;
import com.example.backend.projects.project.dto.UpdateProjectRequest;
import com.example.backend.projects.project.entities.Project;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.projects.project.repositories.ProjectRepository;
import com.example.backend.projects.project_members.dto.CreateProjectMemberRequest;
import com.example.backend.projects.project_members.dto.ProjectMemberResponse;
import com.example.backend.projects.project_members.dto.UpdateProjectMemberRequest;
import com.example.backend.projects.project_members.entities.ProjectMember;
import com.example.backend.projects.project_members.entities.ProjectMemberId;
import com.example.backend.projects.project_members.exceptions.AlreadyExistProjectMemberException;
import com.example.backend.projects.project_members.exceptions.InvalidProjectRoleScopeException;
import com.example.backend.projects.project_members.exceptions.MemberNotFoundInProjectException;
import com.example.backend.projects.project_members.exceptions.ProjectMemberNotFoundException;
import com.example.backend.projects.project_members.repositories.ProjectMemberRepository;
import com.example.backend.roles.entities.Role;
import com.example.backend.roles.enums.RoleScope;
import com.example.backend.roles.exceptions.RoleNotFoundException;
import com.example.backend.roles.repositories.RoleRepository;
import com.example.backend.users.entities.User;
import com.example.backend.users.exceptions.UserNotFoundException;
import com.example.backend.users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    public ProjectMemberService(
            ProjectMemberRepository projectMemberRepository,
            ProjectRepository projectRepository,
            RoleRepository roleRepository,
            UserRepository userRepository
    ) {
        this.projectMemberRepository = projectMemberRepository;
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    //ADD MEMBER TO PROJECT
    public ProjectMemberResponse addMemberToProject(
            Long projectId,
            CreateProjectMemberRequest request
    ) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException(request.getRoleId()));

        ProjectMemberId id = new ProjectMemberId();
        id.setProjectId(projectId);
        id.setUserId(request.getUserId());

        if (projectMemberRepository.existsById(id)){
            throw new AlreadyExistProjectMemberException(user.getId(),  projectId);
        }

        if (role.getScope() != RoleScope.PROJECT){
            throw new InvalidProjectRoleScopeException(request.getRoleId());
        }

        ProjectMember projectMember = new ProjectMember();

        projectMember.setId(id);
        projectMember.setProject(project);
        projectMember.setUser(user);
        projectMember.setRole(role);

        projectMemberRepository.save(projectMember);

        return toResponse(projectMember);
    }

    //GET ALL PROJECT MEMBER
    public List<ProjectMemberResponse> getAllProjectMembers(){
        return projectMemberRepository.getAllProjectMembers()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET ALL PROJECT MEMBER BY PROJECT ID
    public List<ProjectMemberResponse> getAllProjectMembersByProjectId(Long projectId){
        if (!projectRepository.existsById(projectId)){
            throw new ProjectNotFoundException(projectId);
        }

        return projectMemberRepository.findAllByProjectId(projectId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //UPDATE PROJECT MEMBER ROLE
    public ProjectMemberResponse updateProjectMember(
            Long userId,
            UpdateProjectMemberRequest request
    ){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException(request.getRoleId()));

        if (role.getScope() != RoleScope.PROJECT){
            throw new InvalidProjectRoleScopeException(request.getRoleId());
        }

        ProjectMember member = projectMemberRepository.findByUser(user)
                        .orElseThrow(() -> new ProjectMemberNotFoundException(userId));

        member.setRole(role);

        projectMemberRepository.save(member);

        return toResponse(member);
    }

    //DELETE PROJECT MEMBER
    @Transactional
    public void deleteProjectMember(
            Long projectId,
            Long userId
    ){
        if (!projectMemberRepository.existsByProjectIdAndUserId(projectId, userId)){
            throw new MemberNotFoundInProjectException(projectId, userId);
        }

        projectMemberRepository.deleteByProjectIdAndUserId(projectId, userId);
    }

    //TO RESPONSE
    public ProjectMemberResponse toResponse(ProjectMember  projectMember) {
        ProjectMemberResponse response = new ProjectMemberResponse();

        response.setUserId(projectMember.getUser().getId());
        response.setUserName(projectMember.getUser().getFullName());
        response.setEmail(projectMember.getUser().getEmail());
        response.setRoleId(projectMember.getRole().getId());
        response.setRoleName(projectMember.getRole().getName());
        response.setProjectId(projectMember.getProject().getId());
        response.setProjectName(projectMember.getProject().getName());

        return response;
    }
}
