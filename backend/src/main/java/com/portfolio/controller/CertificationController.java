package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.CertificationRequest;
import com.portfolio.entity.Certification;
import com.portfolio.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @GetMapping("/api/public/certifications")
    public ResponseEntity<ApiResponse<List<Certification>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(certificationService.getAll()));
    }

    @PostMapping("/api/admin/certificates")
    public ResponseEntity<ApiResponse<Certification>> create(@RequestBody CertificationRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Certification created", certificationService.create(request)));
    }

    @PutMapping("/api/admin/certificates/{id}")
    public ResponseEntity<ApiResponse<Certification>> update(@PathVariable Long id, @RequestBody CertificationRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Certification updated", certificationService.update(id, request)));
    }

    @DeleteMapping("/api/admin/certificates/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        certificationService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Certification deleted", null));
    }

    @PutMapping("/api/admin/certificates/{id}/image")
    public ResponseEntity<ApiResponse<Certification>> uploadImage(@PathVariable Long id, @RequestParam("file") org.springframework.web.multipart.MultipartFile file) throws java.io.IOException {
        return ResponseEntity.ok(ApiResponse.success("Certification image updated", certificationService.uploadImage(id, file)));
    }
}
