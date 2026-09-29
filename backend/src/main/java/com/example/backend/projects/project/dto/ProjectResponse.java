package com.example.backend.projects.project.dto;

import lombok.Data;

@Data
public class ProjectResponse {
    private Long id;
    private Long organizationId;
    private String name;
}
