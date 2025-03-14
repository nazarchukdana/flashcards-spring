package org.example.s29888tpo2.service;

import org.example.s29888tpo2.Entry;
import org.example.s29888tpo2.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@PropertySource("classpath:new.properties")
class FileService implements StorageService {
    private final String FILE_PATH;

    public FileService(@Value("${pl.edu.pja.tpo02.filename}") String filePath) {
        FILE_PATH = filePath;
    }

    public void loadEntries(EntryRepository repository){
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("--");
                if (parts.length == 3) {
                    repository.addNewEntry(parts[0].trim(), parts[1].trim(), parts[2].trim());
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public void saveEntries(EntryRepository repository) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_PATH), true)) {
            for (Entry entry : repository.getAll()) {
                writer.println(entry);
            }
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}
