package com.portfolio.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CertificationRequest {
    private String title;
    private String organization;
    private LocalDate issueDate;
    private String credentialUrl;
    private String imageUrl;
    private String credentialId;
}
