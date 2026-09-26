package com.example.backend.organization.service;

import com.example.backend.common.exceptions.OrganizationNotFoundException;
import com.example.backend.entities.Organization;
import com.example.backend.organization.dto.CreateOrganizationRequest;
import com.example.backend.organization.dto.OrganizationResponse;
import com.example.backend.organization.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    //CREATE ORGANIZATION
    public OrganizationResponse createOrganization(
            CreateOrganizationRequest request
    ){
        Organization organization = new Organization();
        organization.setName(request.getName());

        Organization saved = organizationRepository.save(organization);

        OrganizationResponse response = new OrganizationResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setCreated_at(saved.getCreatedAt());

        return response;
    }

    //GET ORGANIZATION BY ID
    public OrganizationResponse getOrganizationById(Long id){
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));

        OrganizationResponse response = new OrganizationResponse();
        response.setId(organization.getId());
        response.setName(organization.getName());
        response.setCreated_at(organization.getCreatedAt());

        return response;
    }
}
