package com.example.backend.projects.project_members.controllers;

import com.example.backend.projects.project_members.dto.CreateProjectMemberRequest;
import com.example.backend.projects.project_members.dto.ProjectMemberResponse;
import com.example.backend.projects.project_members.dto.UpdateProjectMemberRequest;
import com.example.backend.projects.project_members.services.ProjectMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(
            ProjectMemberService projectMemberService
    ) {
        this.projectMemberService = projectMemberService;
    }

    //ADD MEMBER TO PROJECT
    @PostMapping("/{projectId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectMemberResponse addMember(
            @PathVariable Long projectId,
            @Valid @RequestBody CreateProjectMemberRequest request
    ){
        return projectMemberService.addMemberToProject(projectId, request);
    }

    //GET ALL PROJECT MEMBER
    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectMemberResponse> getAllMembers(){
        return projectMemberService.getAllProjectMembers();
    }

    //GET ALL PROJECT MEMBER BY PROJECT ID
    @GetMapping("/{projectId}/members")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectMemberResponse> getAllMembersByProjectId(
            @PathVariable Long projectId
    ){
        return projectMemberService.getAllProjectMembersByProjectId(projectId);
    }

    //UPDATE PROJECT MEMBER ROLE
    @PatchMapping("/members/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectMemberResponse updateMember(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateProjectMemberRequest request
    ){
        return projectMemberService.updateProjectMember(userId, request);
    }

    //DELETE PROJECT MEMBER
    @DeleteMapping("/{projectId}/members/{userId}")
    public void deleteProjectMember(
            @PathVariable Long projectId,
            @PathVariable Long userId
    ){
        projectMemberService.deleteProjectMember(projectId, userId);
    }
}
