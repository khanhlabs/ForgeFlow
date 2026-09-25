package com.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TaskAssigneeId implements Serializable {
    @Serial
    private static final long serialVersionUID = 4758301313433831115L;
    @NotNull
    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;


}