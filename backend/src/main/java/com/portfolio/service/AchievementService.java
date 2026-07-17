package com.portfolio.service;

import com.portfolio.dto.AchievementRequest;
import com.portfolio.entity.Achievement;
import com.portfolio.repository.AchievementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.portfolio.util.FileStorageUtil;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final FileStorageUtil fileStorageUtil;

    public List<Achievement> getAll() {
        return achievementRepository.findAllByOrderBySortOrderAscDateDesc();
    }

    public Achievement getById(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Achievement not found: " + id));
    }

    public Achievement create(AchievementRequest req) {
        return achievementRepository.save(Achievement.builder()
                .title(req.getTitle()).description(req.getDescription())
                .date(req.getDate()).icon(req.getIcon())
                .category(req.getCategory()).sortOrder(req.getSortOrder()).build());
    }

    public Achievement update(Long id, AchievementRequest req) {
        Achievement ach = getById(id);
        ach.setTitle(req.getTitle()); ach.setDescription(req.getDescription());
        ach.setDate(req.getDate()); ach.setIcon(req.getIcon());
        ach.setCategory(req.getCategory()); ach.setSortOrder(req.getSortOrder());
        return achievementRepository.save(ach);
    }

    public void delete(Long id) { achievementRepository.deleteById(id); }

    @Transactional
    public Achievement uploadImage(Long id, MultipartFile file) throws IOException {
        Achievement ach = getById(id);
        String fileUrl = fileStorageUtil.storeFile(file, "achievements");
        ach.setImageUrl(fileUrl);
        return achievementRepository.save(ach);
    }
}
