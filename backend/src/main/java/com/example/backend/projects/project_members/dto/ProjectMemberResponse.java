package com.example.backend.projects.project_members.dto;

import lombok.Data;

@Data
public class ProjectMemberResponse {
    private Long userId;
    private String userName;
    private String email;
    private Long roleId;
    private String roleName;
    private Long projectId;
    private String projectName;
}
