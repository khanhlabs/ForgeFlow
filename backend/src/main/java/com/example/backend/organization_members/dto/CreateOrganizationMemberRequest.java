package com.example.backend.organization_members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrganizationMemberRequest {

    @NotNull
    private long userId;
    @NotNull
    private long roleId;
}
