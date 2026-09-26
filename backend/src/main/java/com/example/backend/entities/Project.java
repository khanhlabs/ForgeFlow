package com.example.backend.entities;

import com.example.backend.organization.entities.Organization;
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
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "project")
    private Set<Label> labels = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<ProjectMember> projectMembers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Sprint> sprints = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks = new LinkedHashSet<>();


}