package com.example.backend.sprints.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateSprintRequest {
    @NotNull
    @Size(max = 100)
    private String name;
}
