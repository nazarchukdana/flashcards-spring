package org.example.s29888tpo2;

import java.util.Map;

public class Entry {
    private final Map<String, String> translations;

    public Entry(Map<String, String> translations) {
        this.translations = translations;
    }
    public String getTranslation(String languageCode) {
        return translations.getOrDefault(languageCode, "UNKNOWN");
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        translations.forEach((key, value) -> sb.append(value).append(" -- "));
        if (sb.length() > 4) {
            sb.setLength(sb.length() - 4);
        }
        return sb.toString();
    }
}
