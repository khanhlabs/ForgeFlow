package com.example.backend.users.entities;

import com.example.backend.attachments.entities.Attachment;
import com.example.backend.projects.project_members.entities.ProjectMember;
import com.example.backend.tasks.task.entities.Task;
import com.example.backend.tasks.task_comment.entities.TaskComment;
import com.example.backend.organizations.organization_member.entities.OrganizationMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "full_name", unique = true, nullable = false, length = 50)
    private String fullName;

    @Size(max = 254)
    @NotNull
    @Column(name = "email", unique = true, nullable = false, length = 254)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @NotNull
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "uploadedBy")
    private Set<Attachment> attachments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<OrganizationMember> organizationMembers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ProjectMember> projectMembers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TaskComment> taskComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createdBy")
    private Set<Task> tasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "assignee")
    private Set<Task> assignedTasks = new LinkedHashSet<>();


}
