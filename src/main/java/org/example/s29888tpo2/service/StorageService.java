package org.example.s29888tpo2.service;

import org.example.s29888tpo2.EntryRepository;

public interface StorageService {
    void loadEntries(EntryRepository repository, int languagesCount);
    void saveEntries(EntryRepository repository);
}
