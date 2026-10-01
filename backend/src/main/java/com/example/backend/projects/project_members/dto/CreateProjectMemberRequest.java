package com.example.backend.projects.project_members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProjectMemberRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long roleId;
}
