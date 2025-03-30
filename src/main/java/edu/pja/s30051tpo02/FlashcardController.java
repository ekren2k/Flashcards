package edu.pja.s30051tpo02;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.Scanner;

@Component

public class FlashcardController {

    private final FileService fileService;
    private Formatter formatter;

    public FlashcardController(Formatter formatter, FileService fileService) {
        this.formatter = formatter;
        this.fileService = fileService;
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("exit")) {
            System.out.println("Welcome to the Flashcards app. Available commands: addword, display, test, exit");
            input = scanner.nextLine();
            handleCommand(input);
        }
        scanner.close();
    }
    private void handleCommand(String input) {
        switch (input) {
            case "addword" -> addwordCommand();
            case "display" -> displayCommand();
            case "test" -> testCommand();
            case "exit" -> exitCommand();
            default -> System.out.println("Invalid command");
        }
    }

    private void addwordCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an english word: ");
        String word = scanner.nextLine();
        System.out.println("Enter its translation to Polish: ");
        String t1 = scanner.nextLine();
        System.out.println("Enter its translation to German: ");
        String t2 = scanner.nextLine();
        Entry entry = new Entry(word, t1, t2);
        fileService.getEntryRepository().addEntry(entry);
        fileService.writeToCSV(entry);
        System.out.println("The word was successfully added.");
        System.out.println();
    }

    private void displayCommand() {
        System.out.println("list of all available translations:");
        for (Entry e : fileService.getEntryRepository().getEntries())
            System.out.println(formatter.format(e.toString()));
        System.out.println();
    }

    private void exitCommand() {
        System.exit(0);
    }

    private void testCommand() {
        Entry word = getRandomWord();
        System.out.println("You will receive an english translation of the word. Write the translation in two other languages to complete the test.");
        System.out.println("your word is: " + word.getEnglishTranslation());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter translation to polish");
        String t1 = scanner.nextLine();
        System.out.println("Enter translation to german");
        String t2 = scanner.nextLine();
        if (t1.equalsIgnoreCase(word.getPolishTranslation())) {
            if (t2.equalsIgnoreCase(word.getGermanTranslation())) {
                System.out.println("You have successfully translated the word.");
            }
            else System.out.println("German translation is wrong, you failed the test.");
        }
        else System.out.println("Polish translation is wrong, you failed the test.");
        System.out.println();
    }

    private Entry getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(fileService.getEntryRepository().getEntries().size());
        return fileService.getEntryRepository().getEntries().get(index);
    }
}
