package com.example.backend.sprints.repositories;

import com.example.backend.sprints.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SprintRepository extends JpaRepository<Sprint, Long> {

    boolean existsByProjectIdAndName(Long projectId, String name);


}
