package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.SkillRequest;
import com.portfolio.entity.Skill;
import com.portfolio.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping("/api/public/skills")
    public ResponseEntity<ApiResponse<List<Skill>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(skillService.getAllSkills()));
    }

    @PostMapping("/api/admin/skills")
    public ResponseEntity<ApiResponse<Skill>> create(@RequestBody SkillRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Skill created", skillService.create(request)));
    }

    @PutMapping("/api/admin/skills/{id}")
    public ResponseEntity<ApiResponse<Skill>> update(@PathVariable Long id, @RequestBody SkillRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Skill updated", skillService.update(id, request)));
    }

    @DeleteMapping("/api/admin/skills/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Skill deleted", null));
    }
}
