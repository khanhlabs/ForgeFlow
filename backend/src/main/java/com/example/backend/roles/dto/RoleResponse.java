package com.example.backend.roles.dto;

import com.example.backend.roles.enums.RoleScope;
import lombok.Data;

@Data
public class RoleResponse {

    private Long id;
    private String name;
    private RoleScope scope;
}
