package com.example.backend.organization.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OrganizationResponse {

    private Long id;
    private String name;
    private Instant created_at;
}
