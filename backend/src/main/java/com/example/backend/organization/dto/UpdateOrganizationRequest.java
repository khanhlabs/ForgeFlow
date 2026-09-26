package com.example.backend.organization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateOrganizationRequest {
    @NotBlank
    @Size(max = 150)
    private String name;

}
