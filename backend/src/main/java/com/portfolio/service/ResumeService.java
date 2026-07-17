package com.portfolio.service;

import com.portfolio.entity.Resume;
import com.portfolio.repository.ResumeRepository;
import com.portfolio.util.FileStorageUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final FileStorageUtil fileStorageUtil;

    public Resume getActiveResume() {
        return resumeRepository.findByIsActiveTrue()
                .orElseThrow(() -> new EntityNotFoundException("No active resume found"));
    }

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    @Transactional
    public Resume uploadResume(MultipartFile file) throws IOException {
        // Deactivate all existing resumes
        resumeRepository.findAll().forEach(r -> {
            r.setIsActive(false);
            resumeRepository.save(r);
        });

        String fileUrl = fileStorageUtil.storeFile(file, "resume");
        Resume resume = Resume.builder()
                .fileName(file.getOriginalFilename())
                .fileUrl(fileUrl)
                .version("v" + (resumeRepository.count() + 1))
                .uploadedAt(LocalDateTime.now())
                .isActive(true)
                .build();
        return resumeRepository.save(resume);
    }

    public void setActive(Long id) {
        resumeRepository.findAll().forEach(r -> {
            r.setIsActive(r.getId().equals(id));
            resumeRepository.save(r);
        });
    }
}
