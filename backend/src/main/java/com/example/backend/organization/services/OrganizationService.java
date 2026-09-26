package com.example.backend.organization.services;

import com.example.backend.organization.dto.UpdateOrganizationRequest;
import com.example.backend.organization.exceptions.OrganizationNotFoundException;
import com.example.backend.organization.entities.Organization;
import com.example.backend.organization.dto.CreateOrganizationRequest;
import com.example.backend.organization.dto.OrganizationResponse;
import com.example.backend.organization.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return toResponse(saved);
    }

    //GET ORGANIZATION BY ID
    public OrganizationResponse getOrganizationById(Long id){
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));

        return toResponse(organization);
    }

    //GET ALL ORGANIZATION
    public List<OrganizationResponse> getAllOrganizations(){
        return organizationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //DELETE ORGANIZATION BY ID
    public void deleteOrganizationById(Long id){
        if (!organizationRepository.existsById(id)){
            throw new OrganizationNotFoundException(id);
        }
        organizationRepository.deleteById(id);
    }

    //UPDATE ORGANIZATION
    public OrganizationResponse updateOrganization(Long id, UpdateOrganizationRequest request){
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));

        organization.setName(request.getName());
        Organization saved = organizationRepository.save(organization);

        return toResponse(saved) ;
    }

    //TO RESPONSE
    public OrganizationResponse toResponse(Organization organization){
        OrganizationResponse response = new OrganizationResponse();

        response.setId(organization.getId());
        response.setName(organization.getName());
        response.setCreated_at(organization.getCreatedAt());

        return response;
    }
}
