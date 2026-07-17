package com.portfolio.repository;

import com.portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByFeaturedTrue();

    @Query("SELECT p FROM Project p WHERE " +
           "(:search IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "ORDER BY p.featured DESC, p.createdDate DESC")
    List<Project> searchProjects(String search);

    @Query("SELECT p FROM Project p ORDER BY p.featured DESC, p.createdDate DESC")
    List<Project> findAllOrderedByFeatured();
}
