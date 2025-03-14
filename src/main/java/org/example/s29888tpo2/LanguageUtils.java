package org.example.s29888tpo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:new.properties")
public class LanguageUtils {

    private final Map<String, String> languages;

    public LanguageUtils(@Value("${languages}") String languagesConfig) {
        this.languages = new LinkedHashMap<>();
        String[] languageArray = languagesConfig.split(",");
        for (String lang : languageArray) {
            String[] langParts = lang.split(":");
            if (langParts.length == 2) {
                languages.put(langParts[0].trim(), langParts[1].trim());
            }
        }
    }
    public List<String> getLanguageCodes() {
        return languages.keySet().stream().toList();
    }
    public boolean isValidLanguage(String language) {
        return languages.containsKey(language);
    }
    public String getFullLanguage(String language) {
        return languages.getOrDefault(language, "UNKNOWN");
    }
    public int languagesSize() {
        return languages.size();
    }
}
