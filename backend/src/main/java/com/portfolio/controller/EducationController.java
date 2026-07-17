package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.EducationRequest;
import com.portfolio.entity.Education;
import com.portfolio.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @GetMapping("/api/public/education")
    public ResponseEntity<ApiResponse<List<Education>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(educationService.getAll()));
    }

    @PostMapping("/api/admin/education")
    public ResponseEntity<ApiResponse<Education>> create(@RequestBody EducationRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Education created", educationService.create(request)));
    }

    @PutMapping("/api/admin/education/{id}")
    public ResponseEntity<ApiResponse<Education>> update(@PathVariable Long id, @RequestBody EducationRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Education updated", educationService.update(id, request)));
    }

    @DeleteMapping("/api/admin/education/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        educationService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Education deleted", null));
    }
}
