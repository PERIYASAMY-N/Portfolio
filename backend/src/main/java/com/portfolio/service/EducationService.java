package com.portfolio.service;

import com.portfolio.dto.EducationRequest;
import com.portfolio.entity.Education;
import com.portfolio.repository.EducationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class EducationService {

    private final EducationRepository educationRepository;

    public List<Education> getAll() {
        return educationRepository.findAllByOrderBySortOrderAscStartDateDesc();
    }

    public Education getById(Long id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found: " + id));
    }

    public Education create(EducationRequest req) {
        return educationRepository.save(Education.builder()
                .institution(req.getInstitution()).degree(req.getDegree())
                .fieldOfStudy(req.getFieldOfStudy()).startDate(req.getStartDate())
                .endDate(req.getEndDate()).current(req.getCurrent())
                .description(req.getDescription()).grade(req.getGrade())
                .logoUrl(req.getLogoUrl()).sortOrder(req.getSortOrder()).build());
    }

    public Education update(Long id, EducationRequest req) {
        Education edu = getById(id);
        edu.setInstitution(req.getInstitution()); edu.setDegree(req.getDegree());
        edu.setFieldOfStudy(req.getFieldOfStudy()); edu.setStartDate(req.getStartDate());
        edu.setEndDate(req.getEndDate()); edu.setCurrent(req.getCurrent());
        edu.setDescription(req.getDescription()); edu.setGrade(req.getGrade());
        edu.setLogoUrl(req.getLogoUrl()); edu.setSortOrder(req.getSortOrder());
        return educationRepository.save(edu);
    }

    public void delete(Long id) { educationRepository.deleteById(id); }
}
