package com.example.backend.organizations.organization.repositories;

import com.example.backend.organizations.organization.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
