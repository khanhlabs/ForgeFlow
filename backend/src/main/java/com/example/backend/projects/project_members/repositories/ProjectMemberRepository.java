package com.example.backend.projects.project_members.repositories;

import com.example.backend.projects.project_members.entities.ProjectMember;
import com.example.backend.projects.project_members.entities.ProjectMemberId;
import com.example.backend.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {
    @Query("""
        SELECT pm FROM ProjectMember pm
        JOIN FETCH pm.role
        JOIN FETCH pm.user
        JOIN FETCH pm.project
    """)
    public List<ProjectMember> getAllProjectMembers();

    @Query("""
        SELECT pm FROM ProjectMember pm
        JOIN FETCH pm.role
        JOIN FETCH pm.user
        JOIN FETCH pm.project
        WHERE pm.project.id = :projectId
    """)
    List<ProjectMember> findAllByProjectId(Long projectId);

    @Query("""
        SELECT pm FROM ProjectMember pm
        JOIN FETCH pm.role
        JOIN FETCH pm.user
        JOIN FETCH pm.project
        WHERE pm.user = :user
    """)
    Optional<ProjectMember> findByUser(User user);

    boolean existsByProjectIdAndUserId(Long projectId, Long userId);

    void deleteByProjectIdAndUserId(Long projectId, Long userId);

    boolean existsByProjectId(Long projectId);
}
