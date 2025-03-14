package org.example.s29888tpo2.repository;

import org.example.s29888tpo2.Entry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class EntryRepository implements DataRepository<Entry> {
    private final List<Entry> entries;

    public EntryRepository() {
        this.entries = new ArrayList<>();
    }

    @Override
    public void add(Entry entry) {
        entries.add(entry);
    }

    public void addNewEntry(String english, String polish, String german) {
        entries.add(new Entry(english, polish, german));
    }

    @Override
    public List<Entry> getAll() {
        return entries;
    }
}
