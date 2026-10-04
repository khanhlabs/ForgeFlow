package com.example.backend.sprints.repositories;

import com.example.backend.sprints.dto.SprintResponse;
import com.example.backend.sprints.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface SprintRepository extends JpaRepository<Sprint, Long> {

    boolean existsByProjectIdAndName(Long projectId, String name);

    @Query("""
        SELECT s FROM Sprint s
        JOIN FETCH s.project
    """)
    List<Sprint> getAllSprints();

    @Query("""
        SELECT s FROM Sprint s
        JOIN FETCH s.project
         WHERE s.id = :sprintId
    """)
    Optional<Sprint> getBySprintId(Long sprintId);

    @Query("""
        SELECT s FROM Sprint s
        JOIN FETCH s.project
        WHERE s.project.id = :projectId
        AND s.id = :id
    """)
    Optional<Sprint> findByProjectIdAndId(Long projectId, Long id);

    boolean existsByProjectId(Long projectId);

    List<Sprint> findByProjectId(Long projectId);
}
