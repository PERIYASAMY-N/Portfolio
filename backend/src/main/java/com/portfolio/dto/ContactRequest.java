package com.portfolio.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class ContactRequest {
    @NotBlank
    private String name;

    @Email @NotBlank
    private String email;

    private String subject;

    @NotBlank
    private String body;
}
