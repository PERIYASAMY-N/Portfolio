package com.portfolio.service;

import com.portfolio.dto.ProjectRequest;
import com.portfolio.entity.Project;
import com.portfolio.entity.ProjectImage;
import com.portfolio.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.portfolio.util.FileStorageUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FileStorageUtil fileStorageUtil;

    public List<Project> getAll(String search) {
        if (search != null && !search.isBlank()) {
            return projectRepository.searchProjects(search);
        }
        return projectRepository.findAllOrderedByFeatured();
    }

    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
    }

    public List<Project> getFeatured() {
        return projectRepository.findByFeaturedTrue();
    }

    @Transactional
    public Project create(ProjectRequest request) {
        Project project = Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .githubUrl(request.getGithubUrl())
                .liveUrl(request.getLiveUrl())
                .featured(request.getFeatured() != null ? request.getFeatured() : false)
                .createdDate(request.getCreatedDate())
                .technologies(request.getTechnologies() != null ? request.getTechnologies() : new ArrayList<>())
                .build();

        if (request.getImages() != null) {
            for (ProjectRequest.ImageRequest img : request.getImages()) {
                ProjectImage image = new ProjectImage();
                image.setImageUrl(img.getImageUrl());
                image.setCaption(img.getCaption());
                image.setIsPrimary(img.getIsPrimary());
                image.setProject(project);
                project.getImages().add(image);
            }
        }
        return projectRepository.save(project);
    }

    @Transactional
    public Project update(Long id, ProjectRequest request) {
        Project project = getById(id);
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setGithubUrl(request.getGithubUrl());
        project.setLiveUrl(request.getLiveUrl());
        project.setFeatured(request.getFeatured() != null ? request.getFeatured() : false);
        project.setCreatedDate(request.getCreatedDate());

        if (request.getTechnologies() != null) {
            project.getTechnologies().clear();
            project.getTechnologies().addAll(request.getTechnologies());
        }

        if (request.getImages() != null) {
            project.getImages().clear();
            for (ProjectRequest.ImageRequest img : request.getImages()) {
                ProjectImage image = new ProjectImage();
                image.setImageUrl(img.getImageUrl());
                image.setCaption(img.getCaption());
                image.setIsPrimary(img.getIsPrimary());
                image.setProject(project);
                project.getImages().add(image);
            }
        }
        return projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    public Project uploadImage(Long id, MultipartFile file) throws IOException {
        Project project = getById(id);
        String fileUrl = fileStorageUtil.storeFile(file, "projects");
        ProjectImage image = new ProjectImage();
        image.setImageUrl(fileUrl);
        image.setCaption("");
        image.setIsPrimary(project.getImages().isEmpty()); // First image becomes primary
        image.setProject(project);
        project.getImages().add(image);
        return projectRepository.save(project);
    }
}
