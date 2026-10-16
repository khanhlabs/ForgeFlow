package com.example.backend.labels.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateLabelRequest {

    @NotNull
    @Size(max = 50)
    private String name;
}
