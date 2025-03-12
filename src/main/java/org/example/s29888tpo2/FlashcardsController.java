package org.example.s29888tpo2;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

enum Command {
    list, add, test, help, quit
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
                        addFlashcard(scanner);
                        break;
                    case test:
                        test(scanner);
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
        System.out.println("_________________________________________");
        System.out.println("Commands to interact with application: \n"+
                "list -- to present the whole dictionary\n" +
                "add -- to add a new flash card\n"+
                "test -- to test the whole dictionary\n"+
                "help -- to show this help message\n"+
                "quit -- to quit the application");
        System.out.println("_________________________________________");
    }
    private void addFlashcard(Scanner scanner) {
        System.out.println("_________________________________________");
        System.out.print("Enter the word in English:\n>> ");
        String english = scanner.nextLine().toLowerCase().trim();
        System.out.print("Enter the word in Polish:\n>> ");
        String polish = scanner.nextLine().toLowerCase().trim();
        System.out.print("Enter the word in German:\n>> ");
        String german = scanner.nextLine().toLowerCase().trim();
        repository.addNewEntry(english, polish, german);
        System.out.println("New flashcard is created!");
        System.out.println("_________________________________________");
    }
    private void test(Scanner scanner) {
        System.out.println("_________________________________________");
        List<Entry> entries = repository.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No flashcards available for testing.");
            return;
        }
        System.out.println("Flashcards Test: You will be asked to translate words.");
        System.out.println("Type 'exit' anytime to stop the test.");

        while (true) {
            Entry entry = entries.get((int) (Math.random() * entries.size()));
            String word = entry.getEnglish();

            System.out.println("Translate this word in POLISH: " + word);
            System.out.print(">> ");

            String userAnswer = scanner.nextLine().trim();

            if (userAnswer.equals("exit")) {
                System.out.println("Exiting test mode. Good job!");
                break;
            }
            String correctAnswer = entry.getPolish();
            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer was: " + correctAnswer);
            }
            System.out.println("Translate this word in GERMAN: " + word);
            System.out.print(">> ");

            userAnswer = scanner.nextLine().trim();

            if (userAnswer.equals("exit")) {
                System.out.println("Exiting test mode. Good job!");
                break;
            }
            correctAnswer = entry.getGerman();
            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer was: " + correctAnswer);
            }
            System.out.println("_________________________________________");
        }
        System.out.println("_________________________________________");
    }

    private void showAllFlashcards() {
        System.out.println("_________________________________________");
        List<Entry> entries = repository.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No flashcards available.");
        }
        else {
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        }
        System.out.println("_________________________________________");
    }
}