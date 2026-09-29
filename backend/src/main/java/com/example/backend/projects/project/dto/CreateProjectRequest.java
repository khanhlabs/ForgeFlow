package com.example.backend.projects.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProjectRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    private Long organizationId;
}
