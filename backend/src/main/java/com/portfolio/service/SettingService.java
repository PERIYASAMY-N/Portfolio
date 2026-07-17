package com.portfolio.service;

import com.portfolio.entity.Setting;
import com.portfolio.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class SettingService {

    private final SettingRepository settingRepository;

    public List<Setting> getAll() {
        return settingRepository.findAll();
    }

    public String getValue(String key) {
        return settingRepository.findBySettingKey(key)
                .map(Setting::getSettingValue).orElse(null);
    }

    public void updateSettings(Map<String, String> updates) {
        updates.forEach((key, value) -> {
            Setting setting = settingRepository.findBySettingKey(key)
                    .orElse(Setting.builder().settingKey(key).build());
            setting.setSettingValue(value);
            settingRepository.save(setting);
        });
    }
}
