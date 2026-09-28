package com.example.backend.organizations.organization_member.controllers;

import com.example.backend.organizations.organization_member.dto.CreateOrganizationMemberRequest;
import com.example.backend.organizations.organization_member.dto.OrganizationMemberResponse;
import com.example.backend.organizations.organization_member.services.OrganizationMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
public class OrganizationMemberController {

    private final OrganizationMemberService organizationMemberService;
    public OrganizationMemberController(
            OrganizationMemberService organizationMemberService
    ) {
        this.organizationMemberService = organizationMemberService;
    }

    @PostMapping("/{organizationId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationMemberResponse addMember(
            @PathVariable Long organizationId,
            @Valid @RequestBody CreateOrganizationMemberRequest request
    ) {
        return organizationMemberService.addOrganizationMember(organizationId, request);
    }

}
