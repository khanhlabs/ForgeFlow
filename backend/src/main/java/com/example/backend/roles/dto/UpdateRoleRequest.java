package com.example.backend.roles.dto;

import com.example.backend.roles.enums.RoleScope;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRoleRequest {
    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    RoleScope scope;
}
