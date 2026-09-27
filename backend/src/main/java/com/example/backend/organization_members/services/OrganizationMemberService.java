package com.example.backend.organization_members.services;

import com.example.backend.entities.User;
import com.example.backend.organization.entities.Organization;
import com.example.backend.organization.exceptions.OrganizationNotFoundException;
import com.example.backend.organization.repositories.OrganizationRepository;
import com.example.backend.organization_members.dto.CreateOrganizationMemberRequest;
import com.example.backend.organization_members.dto.OrganizationMemberResponse;
import com.example.backend.organization_members.entities.OrganizationMember;
import com.example.backend.organization_members.entities.OrganizationMemberId;
import com.example.backend.organization_members.exceptions.OrganizationMemberAlreadyExistException;
import com.example.backend.organization_members.repositories.OrganizationMemberRepository;
import com.example.backend.roles.entities.Role;
import com.example.backend.roles.exceptions.RoleNotFoundException;
import com.example.backend.roles.repositories.RoleRepository;
import com.example.backend.users.exceptions.UserNotFoundException;
import com.example.backend.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

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

        OrganizationMember member = new OrganizationMember();

        member.setId(organizationMemberId);
        member.setOrganization(organization);
        member.setUser(user);
        member.setRole(role);

        organizationMemberRepository.save(member);

        return toResponse(member);
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
