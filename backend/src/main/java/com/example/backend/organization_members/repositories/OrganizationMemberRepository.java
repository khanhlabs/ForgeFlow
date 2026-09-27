package com.example.backend.organization_members.repositories;

import com.example.backend.organization_members.entities.OrganizationMember;
import com.example.backend.organization_members.entities.OrganizationMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember, OrganizationMemberId> {
}
