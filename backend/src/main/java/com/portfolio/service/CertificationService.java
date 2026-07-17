package com.portfolio.service;

import com.portfolio.dto.CertificationRequest;
import com.portfolio.entity.Certification;
import com.portfolio.repository.CertificationRepository;
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
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final FileStorageUtil fileStorageUtil;

    public List<Certification> getAll() {
        return certificationRepository.findAllByOrderByIssueDateDesc();
    }

    public Certification getById(Long id) {
        return certificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certification not found: " + id));
    }

    public Certification create(CertificationRequest req) {
        return certificationRepository.save(Certification.builder()
                .title(req.getTitle()).organization(req.getOrganization())
                .issueDate(req.getIssueDate()).credentialUrl(req.getCredentialUrl())
                .imageUrl(req.getImageUrl()).credentialId(req.getCredentialId()).build());
    }

    public Certification update(Long id, CertificationRequest req) {
        Certification cert = getById(id);
        cert.setTitle(req.getTitle()); cert.setOrganization(req.getOrganization());
        cert.setIssueDate(req.getIssueDate()); cert.setCredentialUrl(req.getCredentialUrl());
        cert.setImageUrl(req.getImageUrl()); cert.setCredentialId(req.getCredentialId());
        return certificationRepository.save(cert);
    }

    public void delete(Long id) { certificationRepository.deleteById(id); }

    @Transactional
    public Certification uploadImage(Long id, MultipartFile file) throws IOException {
        Certification cert = getById(id);
        String fileUrl = fileStorageUtil.storeFile(file, "certificates");
        cert.setImageUrl(fileUrl);
        return certificationRepository.save(cert);
    }
}
