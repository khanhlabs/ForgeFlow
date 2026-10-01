package com.example.backend.organizations.organization_member.services;

import com.example.backend.organizations.organization_member.dto.UpdateOrganizationMemberRequest;
import com.example.backend.organizations.organization_member.exceptions.OrganizationMemberNotFoundException;
import com.example.backend.roles.enums.RoleScope;
import com.example.backend.users.entities.User;
import com.example.backend.organizations.organization.entities.Organization;
import com.example.backend.organizations.organization.exceptions.OrganizationNotFoundException;
import com.example.backend.organizations.organization.repositories.OrganizationRepository;
import com.example.backend.organizations.organization_member.dto.CreateOrganizationMemberRequest;
import com.example.backend.organizations.organization_member.dto.OrganizationMemberResponse;
import com.example.backend.organizations.organization_member.entities.OrganizationMember;
import com.example.backend.organizations.organization_member.entities.OrganizationMemberId;
import com.example.backend.organizations.organization_member.exceptions.InvalidOrganizationRoleScopeException;
import com.example.backend.organizations.organization_member.exceptions.OrganizationMemberAlreadyExistException;
import com.example.backend.organizations.organization_member.repositories.OrganizationMemberRepository;
import com.example.backend.roles.entities.Role;
import com.example.backend.roles.exceptions.RoleNotFoundException;
import com.example.backend.roles.repositories.RoleRepository;
import com.example.backend.users.exceptions.UserNotFoundException;
import com.example.backend.users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationMemberService {

    private final OrganizationMemberRepository organizationMemberRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public OrganizationMemberService(
            OrganizationMemberRepository organizationMemberRepository,
            OrganizationRepository organizationRepository,
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        this.organizationMemberRepository = organizationMemberRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    //ADD ORGANIZATION MEMBER
    public OrganizationMemberResponse addOrganizationMember(
            Long organizationId,
            CreateOrganizationMemberRequest request
    ) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException(request.getRoleId()));

        OrganizationMemberId organizationMemberId = new OrganizationMemberId();
        organizationMemberId.setOrganizationId(organizationId);
        organizationMemberId.setUserId(request.getUserId());

        if (organizationMemberRepository.existsById(organizationMemberId)){
            throw new OrganizationMemberAlreadyExistException(
                    organizationId,
                    request.getUserId());
        }

        if(RoleScope.ORGANIZATION != role.getScope()){
            throw new InvalidOrganizationRoleScopeException(role.getId());
        }

        OrganizationMember member = new OrganizationMember();

        member.setId(organizationMemberId);
        member.setOrganization(organization);
        member.setUser(user);
        member.setRole(role);

        organizationMemberRepository.save(member);

        return toResponse(member);
    }

    //GET ALL ORGANIZATION MEMBER
    public List<OrganizationMemberResponse> getAllOrganizationMembers(){
        return organizationMemberRepository.getAllOrganizationMembers()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET ALL ORGANIZATION MEMBER BY ORGANIZATION ID
    public List<OrganizationMemberResponse> getAllOrganizationMembersByOrganizationId(Long organizationId) {
        if (!organizationRepository.existsById(organizationId)){
            throw new OrganizationNotFoundException(organizationId);
        }
        return organizationMemberRepository.findAllByOrganizationId(organizationId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //UPDATE ORGANIZATION MEMBER ROLE
    public OrganizationMemberResponse updateOrganizationMember(
            Long organizationId,
            Long userId,
            UpdateOrganizationMemberRequest request
    ){
        if (!organizationRepository.existsById(organizationId)){
            throw new OrganizationNotFoundException(organizationId);
        }

        OrganizationMember member = organizationMemberRepository
                .findByOrganizationIdAndUserId(organizationId, userId)
                .orElseThrow(() ->
                        new OrganizationMemberNotFoundException(
                                organizationId,
                                userId
                        )
                );

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException(request.getRoleId()));

        if (RoleScope.ORGANIZATION != role.getScope()){
            throw new InvalidOrganizationRoleScopeException(role.getId());
        }

        member.setRole(role);

        organizationMemberRepository.save(member);
        return toResponse(member);
    }

    //DELETE ORGANIZATION MEMBER
    @Transactional
    public void deleteOrganizationMemberById(Long organizationId, Long userId){
        if (!organizationRepository.existsById(organizationId)){
            throw new OrganizationNotFoundException(organizationId);
        } else if (!organizationMemberRepository.existsByOrganizationIdAndUserId(organizationId, userId)) {
            throw new OrganizationMemberNotFoundException(organizationId, userId);
        }

        organizationMemberRepository.deleteByOrganizationIdAndUserId(organizationId, userId);
    }

    //TO RESPONSE
    public OrganizationMemberResponse toResponse(
            OrganizationMember member
    ) {
        OrganizationMemberResponse response = new OrganizationMemberResponse();

        response.setUser_id(member.getUser().getId());
        response.setUser_name(member.getUser().getFullName());
        response.setEmail(member.getUser().getEmail());
        response.setRole_id(member.getRole().getId());
        response.setRole_name(member.getRole().getName());

        return response;
    }
}
