package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.ProjectRequest;
import com.portfolio.entity.Project;
import com.portfolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/api/public/projects")
    public ResponseEntity<ApiResponse<List<Project>>> getAll(
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ApiResponse.success(projectService.getAll(search)));
    }

    @GetMapping("/api/public/projects/{id}")
    public ResponseEntity<ApiResponse<Project>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(projectService.getById(id)));
    }

    @GetMapping("/api/public/projects/featured")
    public ResponseEntity<ApiResponse<List<Project>>> getFeatured() {
        return ResponseEntity.ok(ApiResponse.success(projectService.getFeatured()));
    }

    @PostMapping("/api/admin/projects")
    public ResponseEntity<ApiResponse<Project>> create(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Project created", projectService.create(request)));
    }

    @PutMapping("/api/admin/projects/{id}")
    public ResponseEntity<ApiResponse<Project>> update(@PathVariable Long id, @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Project updated", projectService.update(id, request)));
    }

    @DeleteMapping("/api/admin/projects/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Project deleted", null));
    }

    @PostMapping("/api/admin/projects/{id}/image")
    public ResponseEntity<ApiResponse<Project>> uploadImage(@PathVariable Long id, @RequestParam("file") org.springframework.web.multipart.MultipartFile file) throws java.io.IOException {
        return ResponseEntity.ok(ApiResponse.success("Project image added", projectService.uploadImage(id, file)));
    }
}
