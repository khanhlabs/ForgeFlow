package com.example.backend.projects.project.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProjectRequest {
    @NotNull
    @Size(max = 100)
    private String name;
}
