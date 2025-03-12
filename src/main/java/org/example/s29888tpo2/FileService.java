package org.example.s29888tpo2;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
class FileService {
    private static final String FILE_PATH = "flashcards.txt";

    public FileService() {

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
            for (Entry entry : repository.getAllEntries()) {
                writer.println(entry);
            }
            System.out.println("Entries saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}
