package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.ExperienceRequest;
import com.portfolio.entity.Experience;
import com.portfolio.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping("/api/public/experience")
    public ResponseEntity<ApiResponse<List<Experience>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(experienceService.getAll()));
    }

    @PostMapping("/api/admin/experience")
    public ResponseEntity<ApiResponse<Experience>> create(@RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Experience created", experienceService.create(request)));
    }

    @PutMapping("/api/admin/experience/{id}")
    public ResponseEntity<ApiResponse<Experience>> update(@PathVariable Long id, @RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Experience updated", experienceService.update(id, request)));
    }

    @DeleteMapping("/api/admin/experience/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Experience deleted", null));
    }
}
