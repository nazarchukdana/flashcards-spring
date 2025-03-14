package org.example.s29888tpo2;
import org.example.s29888tpo2.formatter.WordFormatter;
import org.example.s29888tpo2.repository.EntryRepository;
import org.example.s29888tpo2.service.StorageService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {
    list, add, test, help, quit
}

@Controller
public class FlashcardsController {
    private final EntryRepository repository;
    private final StorageService storageService;
    private final WordFormatter wordFormatter;
    private final LanguageUtils languageUtils;
    private final Scanner scanner;

    public FlashcardsController(EntryRepository repository, StorageService storageService, WordFormatter wordFormatter, LanguageUtils languageUtils, Scanner scanner) {
        this.repository = repository;
        this.storageService = storageService;
        this.wordFormatter = wordFormatter;
        this.languageUtils = languageUtils;
        this.scanner = scanner;
        Thread flashcardsThread = new Thread(this::start);
        flashcardsThread.start();
    }
    public void start() {
        storageService.loadEntries(repository, languageUtils.languagesSize());
        System.out.println("Welcome to the Flashcards Application!");
        showMenu();
        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine().trim();
            try {
                Command command = Command.valueOf(line);
                switch (command) {
                    case list:
                        showAllFlashcards();
                        break;
                    case add:
                        addFlashcard();
                        break;
                    case test:
                        test();
                        break;
                    case help:
                        showMenu();
                        break;
                    case quit:
                        System.out.println("Have a good day! See you soon!");
                        storageService.saveEntries(repository);
                        return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command. Type 'help' for available commands.");
            }
        }
    }
    private void showMenu(){
        System.out.println("_________________________________________");
        System.out.println("Commands to interact with application: \n"+
                "list -- to present the whole dictionary\n" +
                "add -- to add a new flash card\n"+
                "test -- to test the whole dictionary\n"+
                "help -- to show this help message\n"+
                "quit -- to quit the application");
        System.out.println("_________________________________________");
    }
    private void addFlashcard() {
        System.out.println("_________________________________________");
        List<String> translations = new ArrayList<>();
        for (String languageCode : languageUtils.getLanguageCodes()){
            String language = languageUtils.getFullLanguage(languageCode);
            System.out.print("Enter the word in "+language+":\n>> ");
            String word = scanner.nextLine().trim();
            translations.add(word);
        }
        try {
            repository.addNewEntry(translations);
            System.out.println("New flashcard is created!");
        } catch (Exception e) {
            System.err.println("Error while creating new entry: " + e.getMessage());
        }
        System.out.println("_________________________________________");
    }
    private void test() {
        System.out.println("_________________________________________");
        List<Entry> entries = repository.getAll();
        if (entries.isEmpty()) {
            System.out.println("No flashcards available for testing.");
            return;
        }
        System.out.println("Flashcards Test: You will be asked to translate words.");
        System.out.println("Type 'exit' anytime to stop the test.");
        System.out.print("Choose the source languageCode for translation ("+String.join(", ", languageUtils.getLanguageCodes())+"):\n>>");
        String languageCode = scanner.nextLine().trim();
        if(!languageUtils.isValidLanguage(languageCode)){
            System.out.println("Invalid source languageCode. English is chosen by default.");
            languageCode = "en";
        }
        while (true) {
            Entry entry = entries.get((int) (Math.random() * entries.size()));
            String word = wordFormatter.format(entry.getTranslation(languageCode));
            for(String targetLanguageCode : languageUtils.getLanguageCodes()) {
                if (targetLanguageCode.equals(languageCode)) {
                    continue;
                }
                String correctAnswer = wordFormatter.format(entry.getTranslation(targetLanguageCode));
                String targetLanguage = languageUtils.getFullLanguage(targetLanguageCode);
                if (!askQuestion("Enter the word in "+targetLanguage+": "+word, correctAnswer)) {
                    return;
                }

            }
            System.out.println("_________________________________________");
        }
    }
    private boolean askQuestion(String question, String correctAnswer) {
        System.out.print(question+"\n>> ");
        String userAnswer = scanner.nextLine().trim();
        if (userAnswer.equals("exit")) {
            System.out.println("Exiting test mode. Good job!");
            return false;
        } else if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect. The correct answer was: " + correctAnswer);
            return true;
        }
    }

    private void showAllFlashcards() {
        System.out.println("_________________________________________");
        List<Entry> entries = repository.getAll();
        if (entries.isEmpty()) {
            System.out.println("No flashcards available.");
        }
        else {
            for (Entry entry : entries) {
                System.out.println(wordFormatter.format(entry.toString()));
            }
        }
        System.out.println("_________________________________________");
    }
}