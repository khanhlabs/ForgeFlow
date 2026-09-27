package com.example.backend.organization_members.dto;

import lombok.Data;

@Data
public class OrganizationMemberResponse {
    private Long user_id;
    private String user_name;
    private String email;
    private Long role_id;
    private String role_name;
}
