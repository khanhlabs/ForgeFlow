package com.example.backend.organizations.organization_member.repositories;

import com.example.backend.organizations.organization_member.entities.OrganizationMember;
import com.example.backend.organizations.organization_member.entities.OrganizationMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember, OrganizationMemberId> {

    @Query("""
        SELECT om FROM OrganizationMember om
        JOIN FETCH om.user
        JOIN FETCH om.role
        WHERE om.organization.id = :organizationId
    """)
    List<OrganizationMember> findAllByOrganizationId(Long organizationId);

    @Query("""
        SELECT om FROM OrganizationMember om
        JOIN FETCH om.user
        JOIN FETCH om.role
        WHERE om.organization.id = :organizationId\s
            AND om.user.id = :userId
   \s""")
    Optional<OrganizationMember> findByOrganizationIdAndUserId(Long organizationId, Long userId);

    void deleteByOrganizationIdAndUserId(Long organizationId, Long userId);

    boolean existsByOrganizationIdAndUserId(Long organizationId, Long userId);
}

