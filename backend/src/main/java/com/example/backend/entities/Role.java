package com.example.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Size(max = 20)
    @NotNull
    @Column(name = "scope", nullable = false, length = 20)
    private String scope;

    @OneToMany(mappedBy = "role")
    private Set<OrganizationMember> organizationMembers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<ProjectMember> projectMembers = new LinkedHashSet<>();


}