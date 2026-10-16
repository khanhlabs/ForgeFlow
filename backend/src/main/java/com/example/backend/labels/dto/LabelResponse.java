package com.example.backend.labels.dto;

import lombok.Data;

@Data
public class LabelResponse {

    private Long id;
    private String name;
    private Long projectId;
}
