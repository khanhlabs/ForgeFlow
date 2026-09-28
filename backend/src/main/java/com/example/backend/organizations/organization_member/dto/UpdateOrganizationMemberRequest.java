package com.example.backend.organizations.organization_member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrganizationMemberRequest {

    @NotNull
    private Long roleId;
}
