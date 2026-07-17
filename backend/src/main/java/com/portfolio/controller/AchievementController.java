package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.AchievementRequest;
import com.portfolio.entity.Achievement;
import com.portfolio.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping("/api/public/achievements")
    public ResponseEntity<ApiResponse<List<Achievement>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(achievementService.getAll()));
    }

    @PostMapping("/api/admin/achievements")
    public ResponseEntity<ApiResponse<Achievement>> create(@RequestBody AchievementRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Achievement created", achievementService.create(request)));
    }

    @PutMapping("/api/admin/achievements/{id}")
    public ResponseEntity<ApiResponse<Achievement>> update(@PathVariable Long id, @RequestBody AchievementRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Achievement updated", achievementService.update(id, request)));
    }

    @DeleteMapping("/api/admin/achievements/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        achievementService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Achievement deleted", null));
    }

    @PutMapping("/api/admin/achievements/{id}/image")
    public ResponseEntity<ApiResponse<Achievement>> uploadImage(@PathVariable Long id, @RequestParam("file") org.springframework.web.multipart.MultipartFile file) throws java.io.IOException {
        return ResponseEntity.ok(ApiResponse.success("Achievement image updated", achievementService.uploadImage(id, file)));
    }
}
