package com.example.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Size(max = 20)
    @NotNull
    @Column(name = "priority", nullable = false, length = 20)
    private String priority;

    @Size(max = 20)
    @NotNull
    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "task")
    private Set<Attachment> attachments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TaskComment> taskComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TaskAssignee> taskAssignees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TaskLabel> taskLabels = new LinkedHashSet<>();


}
