package org.example.s29888tpo2.service;

import org.example.s29888tpo2.repository.EntryRepository;

public interface StorageService {
    void loadEntries(EntryRepository repository);
    void saveEntries(EntryRepository repository);
}
