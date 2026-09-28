package com.example.backend.organizations.organization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateOrganizationRequest {
    @NotBlank
    @Size(max = 150)
    private String name;

}
