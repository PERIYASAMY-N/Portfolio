package com.portfolio.controller;

import com.portfolio.dto.ApiResponse;
import com.portfolio.dto.ContactRequest;
import com.portfolio.entity.Message;
import com.portfolio.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // Public
    @PostMapping("/api/public/messages")
    public ResponseEntity<ApiResponse<Message>> contact(@Valid @RequestBody ContactRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Message sent successfully!", messageService.sendMessage(request)));
    }

    // Admin
    @GetMapping("/api/admin/messages")
    public ResponseEntity<ApiResponse<List<Message>>> getInbox() {
        return ResponseEntity.ok(ApiResponse.success(messageService.getInbox()));
    }

    @GetMapping("/api/admin/messages/archived")
    public ResponseEntity<ApiResponse<List<Message>>> getArchived() {
        return ResponseEntity.ok(ApiResponse.success(messageService.getArchived()));
    }

    @GetMapping("/api/admin/messages/unread-count")
    public ResponseEntity<ApiResponse<Long>> getUnreadCount() {
        return ResponseEntity.ok(ApiResponse.success(messageService.getUnreadCount()));
    }

    @PutMapping("/api/admin/messages/{id}/read")
    public ResponseEntity<ApiResponse<Message>> markRead(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(messageService.markRead(id)));
    }

    @PutMapping("/api/admin/messages/{id}/archive")
    public ResponseEntity<ApiResponse<Message>> archive(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(messageService.archive(id)));
    }

    @DeleteMapping("/api/admin/messages/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        messageService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Message deleted", null));
    }
}
