package com.example.backend.organizations.organization.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OrganizationResponse {

    private Long id;
    private String name;
    private Instant created_at;
}
