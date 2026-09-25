package com.example.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task_labels")
public class TaskLabel {

    @EmbeddedId
    private TaskLabelId id;

    @NotNull
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(
                    name = "task_id",
                    referencedColumnName = "id",
                    insertable = false,
                    updatable = false
            ),
            @JoinColumn(
                    name = "project_id",
                    referencedColumnName = "project_id",
                    insertable = false,
                    updatable = false
            )
    })
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(
                    name = "label_id",
                    referencedColumnName = "id",
                    insertable = false,
                    updatable = false
            ),
            @JoinColumn(
                    name = "project_id",
                    referencedColumnName = "project_id",
                    insertable = false,
                    updatable = false
            )
    })
    private Label label;
}