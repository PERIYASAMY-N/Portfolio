package com.portfolio.service;

import com.portfolio.dto.SkillRequest;
import com.portfolio.entity.Skill;
import com.portfolio.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class SkillService {

    private final SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAllByOrderByCategoryAscSortOrderAsc();
    }

    public Skill getById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + id));
    }

    public Skill create(SkillRequest request) {
        Skill skill = Skill.builder()
                .name(request.getName())
                .category(request.getCategory())
                .percentage(request.getPercentage())
                .icon(request.getIcon())
                .sortOrder(request.getSortOrder())
                .build();
        return skillRepository.save(skill);
    }

    public Skill update(Long id, SkillRequest request) {
        Skill skill = getById(id);
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setPercentage(request.getPercentage());
        skill.setIcon(request.getIcon());
        skill.setSortOrder(request.getSortOrder());
        return skillRepository.save(skill);
    }

    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
