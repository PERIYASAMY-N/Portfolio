package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.entity.Media;
import com.portfolio.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Media>>> getAll(
            @RequestParam(required = false) String category) {
        List<Media> media = category != null ? mediaService.getByCategory(category) : mediaService.getAll();
        return ResponseEntity.ok(ApiResponse.success(media));
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Media>> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "general") String category) throws IOException {
        return ResponseEntity.ok(ApiResponse.success("File uploaded", mediaService.upload(file, category)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("File deleted", null));
    }
}
