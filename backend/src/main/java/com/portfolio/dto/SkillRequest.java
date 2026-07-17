package com.portfolio.dto;

import lombok.Data;

@Data
public class SkillRequest {
    private String name;
    private String category;
    private Integer percentage;
    private String icon;
    private Integer sortOrder;
}
