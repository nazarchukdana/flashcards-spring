package org.example.s29888tpo2;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EntryRepository {
    private final List<Entry> entries;
    public EntryRepository() {
        this.entries = new ArrayList<>();
    }
    public void addEntry(Entry entry) {
        entries.add(entry);
    }
    public void addNewEntry(String english, String polish, String german) {
        Entry entry = new Entry(english, polish, german);
        addEntry(entry);
    }

    public List<Entry> getAllEntries() {
        return entries;
    }
}
