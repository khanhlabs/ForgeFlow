package com.example.backend.organizations.organization_member.repositories;

import com.example.backend.organizations.organization_member.entities.OrganizationMember;
import com.example.backend.organizations.organization_member.entities.OrganizationMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember, OrganizationMemberId> {
}
