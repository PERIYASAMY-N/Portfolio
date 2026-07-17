package com.portfolio.repository;

import com.portfolio.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByCategoryOrderByUploadedAtDesc(String category);
    List<Media> findAllByOrderByUploadedAtDesc();
}
