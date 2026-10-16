package com.example.backend.labels.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateLabelRequest {

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    private Long projectId;
}
