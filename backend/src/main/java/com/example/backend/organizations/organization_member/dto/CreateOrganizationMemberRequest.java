package com.example.backend.organizations.organization_member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrganizationMemberRequest {

    @NotNull
    private long userId;
    @NotNull
    private long roleId;
}
