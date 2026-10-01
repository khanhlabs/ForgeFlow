package com.example.backend.sprints.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SprintResponse {
    private Long id;
    private String name;
    private Long projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;

}
