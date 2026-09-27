package com.example.backend.roles.repositories;

import com.example.backend.roles.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
