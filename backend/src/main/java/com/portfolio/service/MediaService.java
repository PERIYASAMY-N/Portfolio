package com.portfolio.service;

import com.portfolio.entity.Media;
import com.portfolio.repository.MediaRepository;
import com.portfolio.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class MediaService {

    private final MediaRepository mediaRepository;
    private final FileStorageUtil fileStorageUtil;

    public List<Media> getAll() {
        return mediaRepository.findAllByOrderByUploadedAtDesc();
    }

    public List<Media> getByCategory(String category) {
        return mediaRepository.findByCategoryOrderByUploadedAtDesc(category);
    }

    public Media upload(MultipartFile file, String category) throws IOException {
        String fileUrl = fileStorageUtil.storeFile(file, category);
        Media media = Media.builder()
                .fileName(generateFileName(file.getOriginalFilename()))
                .originalName(file.getOriginalFilename())
                .fileUrl(fileUrl)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .category(category)
                .build();
        return mediaRepository.save(media);
    }

    public void delete(Long id) {
        mediaRepository.findById(id).ifPresent(media -> {
            fileStorageUtil.deleteFile(media.getFileUrl());
            mediaRepository.delete(media);
        });
    }

    private String generateFileName(String original) {
        return original != null ? original : "unknown";
    }
}
