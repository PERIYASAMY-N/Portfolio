package com.portfolio.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProfileRequest {
    private String name;
    private String title;
    private String bio;
    private String careerObjective;
    private String email;
    private String phone;
    private String location;
    private String profileImageUrl;
    private String heroBackgroundUrl;
    private List<SocialLinkRequest> socialLinks;

    @Data
    public static class SocialLinkRequest {
        private Long id;
        private String platform;
        private String url;
        private String icon;
    }
}
