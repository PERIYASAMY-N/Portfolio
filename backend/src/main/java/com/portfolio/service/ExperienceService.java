package com.portfolio.service;

import com.portfolio.dto.ExperienceRequest;
import com.portfolio.entity.Experience;
import com.portfolio.repository.ExperienceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public List<Experience> getAll() {
        return experienceRepository.findAllByOrderBySortOrderAscStartDateDesc();
    }

    public Experience getById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experience not found: " + id));
    }

    public Experience create(ExperienceRequest req) {
        return experienceRepository.save(Experience.builder()
                .company(req.getCompany()).role(req.getRole()).type(req.getType())
                .startDate(req.getStartDate()).endDate(req.getEndDate()).current(req.getCurrent())
                .description(req.getDescription()).companyLogoUrl(req.getCompanyLogoUrl())
                .location(req.getLocation()).sortOrder(req.getSortOrder()).build());
    }

    public Experience update(Long id, ExperienceRequest req) {
        Experience exp = getById(id);
        exp.setCompany(req.getCompany()); exp.setRole(req.getRole()); exp.setType(req.getType());
        exp.setStartDate(req.getStartDate()); exp.setEndDate(req.getEndDate()); exp.setCurrent(req.getCurrent());
        exp.setDescription(req.getDescription()); exp.setCompanyLogoUrl(req.getCompanyLogoUrl());
        exp.setLocation(req.getLocation()); exp.setSortOrder(req.getSortOrder());
        return experienceRepository.save(exp);
    }

    public void delete(Long id) { experienceRepository.deleteById(id); }
}
