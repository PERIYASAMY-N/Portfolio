package com.portfolio.service;

import com.portfolio.dto.ContactRequest;
import com.portfolio.entity.Message;
import com.portfolio.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class MessageService {

    private final MessageRepository messageRepository;

    public Message sendMessage(ContactRequest req) {
        Message msg = Message.builder()
                .name(req.getName()).email(req.getEmail())
                .subject(req.getSubject()).body(req.getBody()).build();
        return messageRepository.save(msg);
    }

    public List<Message> getInbox() {
        return messageRepository.findByIsArchivedFalseOrderByCreatedAtDesc();
    }

    public List<Message> getArchived() {
        return messageRepository.findByIsArchivedTrueOrderByCreatedAtDesc();
    }

    public long getUnreadCount() {
        return messageRepository.countByIsReadFalseAndIsArchivedFalse();
    }

    public Message markRead(Long id) {
        Message msg = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message not found: " + id));
        msg.setIsRead(true);
        return messageRepository.save(msg);
    }

    public Message archive(Long id) {
        Message msg = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message not found: " + id));
        msg.setIsArchived(true);
        return messageRepository.save(msg);
    }

    public void delete(Long id) {
        messageRepository.deleteById(id);
    }
}
