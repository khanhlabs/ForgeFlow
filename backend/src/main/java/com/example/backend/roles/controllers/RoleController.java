package com.example.backend.roles.controllers;

import com.example.backend.roles.dto.CreateRoleRequest;
import com.example.backend.roles.dto.RoleResponse;
import com.example.backend.roles.dto.UpdateRoleRequest;
import com.example.backend.roles.enums.RoleScope;
import com.example.backend.roles.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(
            RoleService roleService
    ) {
        this.roleService = roleService;
    }

    //CREATE ROLE
    @PostMapping
    public RoleResponse createRole(
            @Valid @RequestBody CreateRoleRequest request
    ){
        return roleService.createRole(request);
    }

    //GET ALL ROLES
    @GetMapping
    public List<RoleResponse> getAllRoles(){
        return roleService.getAllRoles();
    }

    //GET ROLE BY SCOPE
    @GetMapping("/scopes/{roleScope}")
    public List<RoleResponse> getRolesByScope(
            @PathVariable RoleScope roleScope
    ){
        return roleService.getRoleByScope(roleScope);
    }

    //UPDATE ROLE
    @PutMapping("/{roleId}")
    public RoleResponse updateRole(
            @PathVariable Long roleId,
            @Valid @RequestBody UpdateRoleRequest request
    ){
        return roleService.updateRole(roleId, request);
    }

    //DELETE ROLE
    @DeleteMapping("/{roleId}")
    public void deleteRole(
            @PathVariable Long roleId
    ){
        roleService.deleteRole(roleId);
    }
}
