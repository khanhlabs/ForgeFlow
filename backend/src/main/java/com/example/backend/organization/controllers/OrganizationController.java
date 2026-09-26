package com.example.backend.organization.controllers;

import com.example.backend.organization.dto.CreateOrganizationRequest;
import com.example.backend.organization.dto.OrganizationResponse;
import com.example.backend.organization.dto.UpdateOrganizationRequest;
import com.example.backend.organization.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    //GET ALL ORGANIZATIONS
    @GetMapping
    public List<OrganizationResponse> getAllOrganizations(){
        return organizationService.getAllOrganizations();
    }

    // DELETE ORGANIZATION
    @DeleteMapping("/{id}")
    public void deleteOrganizationById(@PathVariable Long id){
        organizationService.deleteOrganizationById(id);
    }

    //UPDATE ORGANIZATION
    @PatchMapping("/{id}")
    public OrganizationResponse updateOrganization(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrganizationRequest request
    ){
        return organizationService.updateOrganization(id, request);
    }

}
