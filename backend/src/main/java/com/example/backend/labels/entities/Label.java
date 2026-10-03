package com.example.backend.labels.entities;

import com.example.backend.projects.project.entities.Project;
import com.example.backend.tasks.task_label.entities.TaskLabel;
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
@Table(name = "labels", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_id", "name"}),
        @UniqueConstraint(columnNames = {"id", "project_id"})
})
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "label")
    private Set<TaskLabel> taskLabels = new LinkedHashSet<>();

}
