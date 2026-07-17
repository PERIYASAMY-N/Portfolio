package com.portfolio.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProjectRequest {
    private String title;
    private String description;
    private String githubUrl;
    private String liveUrl;
    private Boolean featured;
    private LocalDate createdDate;
    private List<String> technologies;
    private List<ImageRequest> images;

    @Data
    public static class ImageRequest {
        private Long id;
        private String imageUrl;
        private String caption;
        private Boolean isPrimary;
    }
}
