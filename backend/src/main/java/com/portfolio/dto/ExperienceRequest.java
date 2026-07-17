package com.portfolio.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExperienceRequest {
    private String company;
    private String role;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean current;
    private String description;
    private String companyLogoUrl;
    private String location;
    private Integer sortOrder;
}
