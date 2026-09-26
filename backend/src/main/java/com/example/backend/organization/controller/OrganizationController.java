package com.example.backend.organization.controller;

import com.example.backend.entities.Organization;
import com.example.backend.organization.dto.CreateOrganizationRequest;
import com.example.backend.organization.dto.OrganizationResponse;
import com.example.backend.organization.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    //CREATE ORGANIZATION
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationResponse createOrganization(
            @Valid @RequestBody CreateOrganizationRequest request
    ) {
        return organizationService.createOrganization(request);
    }

    //GET ORGANIZATION BY ID
    @GetMapping("/{id}")
    public OrganizationResponse getOrganizationById(@PathVariable Long id){
        return organizationService.getOrganizationById(id);
    }


}
