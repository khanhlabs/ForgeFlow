package com.example.backend.roles.repositories;

import com.example.backend.roles.entities.Role;
import com.example.backend.roles.enums.RoleScope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByNameAndScope(String name, RoleScope scope);

    List<Role> findByScope(RoleScope scope);
}
