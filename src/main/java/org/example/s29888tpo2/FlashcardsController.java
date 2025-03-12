package org.example.s29888tpo2;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

enum Command {
    list, add, help, quit
}

@Controller
public class FlashcardsController {
    private final EntryRepository repository;
    private final FileService fileService;

    public FlashcardsController(EntryRepository repository, FileService fileService) {
        this.repository = repository;
        this.fileService = fileService;
        Thread flashcardsThread = new Thread(this::start);
        flashcardsThread.start();
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        fileService.loadEntries(repository);
        showMenu();
        while (true) {
            String line = scanner.nextLine().toLowerCase().trim();
            System.out.println(line);
            try {
                Command command = Command.valueOf(line);
                switch (command) {
                    case list:
                        showAllFlashcards();
                        break;
                    case add:
                        addFlashcard(scanner);
                        break;
                    case help:
                        showMenu();
                        break;
                    case quit:
                        System.out.println("Have a good day! See you soon!");
                        fileService.saveEntries(repository);
                        return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command. Type 'help' for available commands.");
            }
        }
    }
    private void showMenu(){
        System.out.println("Welcome to the Flashcards Application!");
        System.out.println("Commands to interact with application: \n"+
                "list -- to present the whole dictionary\n" +
                "add -- to add a new flash card\n"+
                "help -- to show this help message\n"+
                "quit -- to quit the application");
    }
    private void addFlashcard(Scanner scanner) {
        System.out.println("Enter the word in English:");
        String english = scanner.nextLine().toLowerCase().trim();
        System.out.println("Enter the word in Polish: ");
        String polish = scanner.nextLine().toLowerCase().trim();
        System.out.println("Enter the word in German: ");
        String german = scanner.nextLine().toLowerCase().trim();
        repository.addNewEntry(english, polish, german);
        System.out.println("New flashcard is created!");
    }

    private void showAllFlashcards() {
        List<Entry> entries = repository.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No flashcards available.");
            return;
        }
        for (Entry entry : entries) {
            System.out.println(entry);
        }
    }
}