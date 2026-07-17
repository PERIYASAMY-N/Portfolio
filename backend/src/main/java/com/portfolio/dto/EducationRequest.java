package com.portfolio.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EducationRequest {
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean current;
    private String description;
    private String grade;
    private String logoUrl;
    private Integer sortOrder;
}
