package com.portfolio.service;

import com.portfolio.dto.ProfileRequest;
import com.portfolio.entity.Profile;
import com.portfolio.entity.SocialLink;
import com.portfolio.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.portfolio.util.FileStorageUtil;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final FileStorageUtil fileStorageUtil;

    public Profile getProfile() {
        return profileRepository.findFirstBy()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
    }

    @Transactional
    public Profile updateProfile(ProfileRequest request) {
        Profile profile = profileRepository.findFirstBy().orElse(new Profile());

        profile.setName(request.getName());
        profile.setTitle(request.getTitle());
        profile.setBio(request.getBio());
        profile.setCareerObjective(request.getCareerObjective());
        profile.setEmail(request.getEmail());
        profile.setPhone(request.getPhone());
        profile.setLocation(request.getLocation());
        profile.setProfileImageUrl(request.getProfileImageUrl());
        profile.setHeroBackgroundUrl(request.getHeroBackgroundUrl());

        if (request.getSocialLinks() != null) {
            profile.getSocialLinks().clear();
            List<SocialLink> links = new ArrayList<>();
            for (ProfileRequest.SocialLinkRequest linkReq : request.getSocialLinks()) {
                SocialLink link = new SocialLink();
                link.setPlatform(linkReq.getPlatform());
                link.setUrl(linkReq.getUrl());
                link.setIcon(linkReq.getIcon());
                link.setProfile(profile);
                links.add(link);
            }
            profile.getSocialLinks().addAll(links);
        }

        return profileRepository.save(profile);
    }
    
    @Transactional
    public Profile uploadProfileImage(MultipartFile file) throws IOException {
        String fileUrl = fileStorageUtil.storeFile(file, "profile");
        Profile profile = profileRepository.findFirstBy().orElse(new Profile());
        profile.setProfileImageUrl(fileUrl);
        return profileRepository.save(profile);
    }

    @Transactional
    public Profile uploadHeroImage(MultipartFile file) throws IOException {
        String fileUrl = fileStorageUtil.storeFile(file, "hero");
        Profile profile = profileRepository.findFirstBy().orElse(new Profile());
        profile.setHeroBackgroundUrl(fileUrl);
        return profileRepository.save(profile);
    }
}
