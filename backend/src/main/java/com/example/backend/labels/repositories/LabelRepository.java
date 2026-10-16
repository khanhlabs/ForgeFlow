package com.example.backend.labels.repositories;

import com.example.backend.labels.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {
    boolean existsByNameAndProjectId(String name, Long projectId);

    List<Label> findByProjectId(Long projectId);
}
