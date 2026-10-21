package com.example.backend.users.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotNull
    @Size(max = 50)
    private String fullName;

    @NotNull
    @Size(max = 254)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

}
