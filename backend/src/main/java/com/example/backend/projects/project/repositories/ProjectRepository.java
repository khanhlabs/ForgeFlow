package com.example.backend.projects.project.repositories;

import com.example.backend.projects.project.dto.ProjectResponse;
import com.example.backend.projects.project.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByOrganizationIdAndId(Long organizationId, Long id);

    List<Project> findAllByOrganizationId(Long organizationId);

    boolean existsByOrganizationId(Long organizationId);
}
