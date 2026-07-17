package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.entity.Setting;
import com.portfolio.service.DashboardService;
import com.portfolio.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final DashboardService dashboardService;
    private final SettingService settingService;

    @GetMapping("/api/admin/dashboard/stats")
    public ResponseEntity<ApiResponse<DashboardService.DashboardStats>> getStats() {
        return ResponseEntity.ok(ApiResponse.success(dashboardService.getStats()));
    }

    @GetMapping("/api/public/settings")
    public ResponseEntity<ApiResponse<List<Setting>>> getSettings() {
        return ResponseEntity.ok(ApiResponse.success(settingService.getAll()));
    }

    @PutMapping("/api/admin/settings")
    public ResponseEntity<ApiResponse<Void>> updateSettings(@RequestBody Map<String, String> updates) {
        settingService.updateSettings(updates);
        return ResponseEntity.ok(ApiResponse.success("Settings updated", null));
    }
}
