package org.example.s29888tpo2.repository;

import org.example.s29888tpo2.Entry;
import org.example.s29888tpo2.LanguageUtils;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EntryRepository implements DataRepository<Entry> {
    private final List<Entry> entries;
    private final LanguageUtils languageUtils;

    public EntryRepository(LanguageUtils languageUtils) {
        this.entries = new ArrayList<>();
        this.languageUtils = languageUtils;
    }

    @Override
    public void add(Entry entry) {
        entries.add(entry);
    }

    public void addNewEntry(List<String> translations){
        if (translations.size() != languageUtils.languagesSize()) {
            throw new IllegalArgumentException("There should be exactly "+languageUtils.languagesSize()+" translations .");
        }
        Map<String, String> translationsMap = new LinkedHashMap<>();
        int i = 0;
        for (String languageCode : languageUtils.getLanguageCodes()) {
            translationsMap.put(languageCode, translations.get(i));
            i++;
        }
        Entry entry = new Entry(translationsMap);
        add(entry);
    }

    @Override
    public List<Entry> getAll() {
        return entries;
    }
}
