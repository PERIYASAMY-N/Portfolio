package com.portfolio.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AchievementRequest {
    private String title;
    private String description;
    private LocalDate date;
    private String icon;
    private String category;
    private Integer sortOrder;
}
