package com.example.backend.organizations.organization_member.controllers;

import com.example.backend.organizations.organization_member.dto.CreateOrganizationMemberRequest;
import com.example.backend.organizations.organization_member.dto.OrganizationMemberResponse;
import com.example.backend.organizations.organization_member.dto.UpdateOrganizationMemberRequest;
import com.example.backend.organizations.organization_member.services.OrganizationMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationMemberController {

    private final OrganizationMemberService organizationMemberService;
    public OrganizationMemberController(
            OrganizationMemberService organizationMemberService
    ) {
        this.organizationMemberService = organizationMemberService;
    }

    //ADD ORGANIZATION MEMBER
    @PostMapping("/{organizationId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationMemberResponse addMember(
            @PathVariable Long organizationId,
            @Valid @RequestBody CreateOrganizationMemberRequest request
    ) {
        return organizationMemberService.addOrganizationMember(organizationId, request);
    }

    //GET ALL ORGANIZATION MEMBER
    @GetMapping("/members")
    public List<OrganizationMemberResponse> getMembers(){
        return organizationMemberService.getAllOrganizationMembers();
    }

    //GET ALL ORGANIZATION MEMBER BY ORGANIZATION ID
    @GetMapping("/{organizationId}/members")
    public List<OrganizationMemberResponse> getAllMembersByOrganizationId(
            @PathVariable Long organizationId
    ) {
        return organizationMemberService.getAllOrganizationMembersByOrganizationId(organizationId);
    }

    //UPDATE ORGANIZATION ROLE
    @PatchMapping("/{organizationId}/members/{userId}")
    public OrganizationMemberResponse updateMember(
            @PathVariable Long organizationId,
            @PathVariable Long userId,
            @Valid @RequestBody UpdateOrganizationMemberRequest request
    ){
        return organizationMemberService.updateOrganizationMember(organizationId, userId, request);
    }

    //DELETE ORGANIZATION MEMBER
    @DeleteMapping("/{organizationId}/members/{userId}")
    public void deleteOrganizationMember(
            @PathVariable Long organizationId,
            @PathVariable Long userId
    ){
        organizationMemberService.deleteOrganizationMemberById(organizationId, userId);
    }

}
