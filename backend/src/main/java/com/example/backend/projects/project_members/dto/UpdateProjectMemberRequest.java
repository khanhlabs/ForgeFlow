package com.example.backend.projects.project_members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProjectMemberRequest {
    @NotNull
    private Long roleId;
}
