package com.portfolio.config;

import com.portfolio.entity.Admin;
import com.portfolio.entity.Profile;
import com.portfolio.entity.Setting;
import com.portfolio.repository.AdminRepository;
import com.portfolio.repository.ProfileRepository;
import com.portfolio.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("null")
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final ProfileRepository profileRepository;
    private final SettingRepository settingRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Override
    public void run(String... args) {
        initAdmin();
        initProfile();
        initSettings();
    }

    private void initAdmin() {
        if (!adminRepository.existsByUsername(adminUsername)) {
            Admin admin = Admin.builder()
                    .username(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .build();
            adminRepository.save(admin);
            log.info("✅ Admin account created: username={}", adminUsername);
        }
    }

    private void initProfile() {
        if (profileRepository.findFirstBy().isEmpty()) {
            Profile profile = Profile.builder()
                    .name("Your Name")
                    .title("Full Stack Developer")
                    .bio("Welcome to my portfolio! I am a passionate developer.")
                    .careerObjective("To build innovative solutions that make a difference.")
                    .email(adminEmail)
                    .location("India")
                    .build();
            profileRepository.save(profile);
            log.info("✅ Default profile created");
        }
    }

    private void initSettings() {
        List<String[]> defaults = List.of(
                new String[]{"theme_color", "#6c63ff", "Primary theme color"},
                new String[]{"dark_mode", "true", "Default dark mode"},
                new String[]{"site_title", "My Portfolio", "Website title"},
                new String[]{"meta_description", "Professional portfolio of a Full Stack Developer", "SEO meta description"}
        );

        for (String[] entry : defaults) {
            if (settingRepository.findBySettingKey(entry[0]).isEmpty()) {
                settingRepository.save(Setting.builder()
                        .settingKey(entry[0])
                        .settingValue(entry[1])
                        .description(entry[2])
                        .build());
            }
        }
    }
}
