package com.example.backend.tasks.task.entities;

import com.example.backend.attachments.entities.Attachment;
import com.example.backend.projects.project.entities.Project;
import com.example.backend.projects.project_members.entities.ProjectMember;
import com.example.backend.sprints.entities.Sprint;
import com.example.backend.tasks.task.enums.TaskPriority;
import com.example.backend.tasks.task.enums.TaskStatus;
import com.example.backend.tasks.task.enums.TaskType;
import com.example.backend.tasks.task_comment.entities.TaskComment;
import com.example.backend.tasks.task_label.entities.TaskLabel;
import com.example.backend.users.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tasks", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "project_id"}))
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TaskStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 20)
    private TaskPriority priority;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private TaskType type;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "task")
    private Set<Attachment> attachments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TaskComment> taskComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TaskLabel> taskLabels = new LinkedHashSet<>();

    @OneToMany(mappedBy = "parentTask")
    private Set<Task> subtasks = new LinkedHashSet<>();
}
