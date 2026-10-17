package com.example.backend.roles.services;

import com.example.backend.organizations.organization_member.repositories.OrganizationMemberRepository;
import com.example.backend.projects.project_members.repositories.ProjectMemberRepository;
import com.example.backend.roles.dto.CreateRoleRequest;
import com.example.backend.roles.dto.RoleResponse;
import com.example.backend.roles.dto.UpdateRoleRequest;
import com.example.backend.roles.entities.Role;
import com.example.backend.roles.enums.RoleScope;
import com.example.backend.roles.exceptions.RoleAlreadyExistsException;
import com.example.backend.roles.exceptions.RoleInMemberException;
import com.example.backend.roles.exceptions.RoleNotFoundException;
import com.example.backend.roles.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final OrganizationMemberRepository organizationMemberRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public RoleService(
            RoleRepository roleRepository,
            OrganizationMemberRepository organizationMemberRepository,
            ProjectMemberRepository projectMemberRepository
    ) {
        this.roleRepository = roleRepository;
        this.organizationMemberRepository = organizationMemberRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    //CREATE ROLE
    public RoleResponse createRole(
            CreateRoleRequest request
    ){
        if (roleRepository.existsByNameAndScope(request.getName(), request.getScope())){
            throw new RoleAlreadyExistsException(request.getName());
        }

        Role role = new Role();

        role.setName(request.getName());
        role.setScope(request.getScope());

        roleRepository.save(role);

        return toResponse(role);
    }

    //GET ALL ROLE
    public List<RoleResponse> getAllRoles(){
       return roleRepository.findAll()
               .stream()
               .map(this::toResponse)
               .toList();
    }

    //GET ROLE BY SCOPE
    public List<RoleResponse> getRoleByScope(RoleScope scope){
        return roleRepository.findByScope(scope)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //UPDATE ROLE
    public RoleResponse updateRole(
            Long roleId,
            UpdateRoleRequest request
    ){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        if (roleRepository.existsByNameAndScope(request.getName(), request.getScope())){
            throw new RoleAlreadyExistsException(request.getName());
        }

        role.setName(request.getName());
        role.setScope(request.getScope());

        roleRepository.save(role);

        return toResponse(role);
    }

    //DELETE ROLE
    public void deleteRole(Long roleId){

        if (!roleRepository.existsById(roleId)){
            throw new RoleNotFoundException(roleId);
        }

        if (organizationMemberRepository.existsByRoleId(roleId)){
            throw new RoleInMemberException("User has this role still exists!");
        }

        if (projectMemberRepository.existsByRoleId(roleId)){
            throw new RoleInMemberException("User has this role still exists!");
        }

        roleRepository.deleteById(roleId);
    }

    //TO RESPONSE
    public RoleResponse toResponse(Role role){
        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setName(role.getName());
        roleResponse.setScope(role.getScope());

        return roleResponse;
    }
}
