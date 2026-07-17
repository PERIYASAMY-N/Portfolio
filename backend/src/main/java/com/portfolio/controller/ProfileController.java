package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.ProfileRequest;
import com.portfolio.entity.Profile;
import com.portfolio.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // Public
    @GetMapping("/api/public/profile")
    public ResponseEntity<ApiResponse<Profile>> getProfile() {
        return ResponseEntity.ok(ApiResponse.success(profileService.getProfile()));
    }

    @GetMapping("/api/public/social-links")
    public ResponseEntity<ApiResponse<java.util.List<com.portfolio.entity.SocialLink>>> getSocialLinks() {
        return ResponseEntity.ok(ApiResponse.success(profileService.getProfile().getSocialLinks()));
    }

    // Admin
    @PutMapping("/api/admin/profile")
    public ResponseEntity<ApiResponse<Profile>> updateProfile(@RequestBody ProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Profile updated", profileService.updateProfile(request)));
    }

    @PutMapping("/api/admin/profile/image")
    public ResponseEntity<ApiResponse<Profile>> uploadProfileImage(@RequestParam("file") org.springframework.web.multipart.MultipartFile file) throws java.io.IOException {
        return ResponseEntity.ok(ApiResponse.success("Profile image updated", profileService.uploadProfileImage(file)));
    }

    @PutMapping("/api/admin/profile/hero-image")
    public ResponseEntity<ApiResponse<Profile>> uploadHeroImage(@RequestParam("file") org.springframework.web.multipart.MultipartFile file) throws java.io.IOException {
        return ResponseEntity.ok(ApiResponse.success("Hero image updated", profileService.uploadHeroImage(file)));
    }
}
