package com.example.backend.sprints.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateSprintRequest {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private Long projectId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
