package drawPJ;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class coordinates input/output with the user
 * Core for all logic User, Lesson, Sketch, Prompt and AppData
 */
// TODO: I am considering the possibility of extracting the menu logic into a separate user interface controller class
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Acts as a very lightweight "in-memory database" for prompts and lessons
     */
    private static final AppData data = new AppData();

    public static void main(String[] args) {
        System.out.println("Welcome to MyDrawingProject!");
        User user = createUser();

        boolean running = true;

        // Main event loop: show menu, handle user choices, repeat until exit.
        while (running) {
            printMainMenu(user);
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> showTodayPrompt();
                case 2 -> addSketch(user);
                case 3 -> manageLessons(user);
                case 4 -> showGallery(user);
                case 5 -> {
                    System.out.println("Goodbye, " + user.getName() + "!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    /**
     * Simple profile creation for the current run
     */
    private static User createUser() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        return new User(1, name, email);
    }

    private static void printMainMenu(User user) {
        System.out.println("\n==== Main Menu ====");
        System.out.println("User: " + user.getName() + " | Streak: " + user.getStreakCount());
        System.out.println("1. View today's prompt");
        System.out.println("2. Add sketch");
        System.out.println("3. View / complete lessons");
        System.out.println("4. View sketch gallery");
        System.out.println("5. Exit");
    }

    /**
     * Currently, this is only the first question on the list
     */
    private static void showTodayPrompt() {
        Prompt p = data.getTodayPrompt();
        System.out.println("\nToday's prompt:");
        System.out.println("  → " + p.getText());
    }

    /**
     * Simulates adding a sketch.
     * There is no real image handling here, we just capture metadata and a note
     */
    private static void addSketch(User user) {
        Prompt p = data.getTodayPrompt();

        System.out.println("\nAdding sketch for prompt:");
        System.out.println("  " + p.getText());
        System.out.print("Enter notes about your sketch (optional): ");

        String notes = scanner.nextLine();
        int id = user.getSketches().size() + 1;

        Sketch s = new Sketch(id, p, LocalDate.now(), notes);
        user.addSketch(s);
        user.incrementStreak();     // Business rule: each saved sketch bumps the streak.

        System.out.println("Sketch saved! Streak is now " + user.getStreakCount());
    }

    /**
     * Allows the user to browse lessons and mark them as completed
     */
    private static void manageLessons(User user) {
        System.out.println("\n=== Lessons ===");

        for (Lesson lesson : data.getLessons()) {
            System.out.println(lesson);
        }

        int id = readInt("Enter lesson ID to view or complete (0 to go back): ");
        if (id == 0) return;

        Lesson lesson = data.findLessonById(id);
        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }

        // Show full lesson details.
        System.out.println("\nLesson Details:");
        System.out.println("Title: " + lesson.getTitle());
        System.out.println("Level: " + lesson.getLevel());
        System.out.println("Summary: " + lesson.getSummary());
        System.out.println("Practice: " + lesson.getPracticeTask());

        if (!lesson.isCompleted()) {
            System.out.print("Mark lesson as completed? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("y")) {
                user.markLessonCompleted(lesson);
                System.out.println("Lesson marked as completed!");
            }
        } else {
            System.out.println("You already completed this lesson.");
        }
    }

    /**
     * Simple read-only gallery for the user’s sketches
     */
    private static void showGallery(User user) {
        System.out.println("\n=== Sketch Gallery ===");

        if (user.getSketches().isEmpty()) {
            System.out.println("No sketches yet.");
            return;
        }

        for (Sketch s : user.getSketches()) {
            System.out.println(s);
        }
    }

    /**
     * Helper to robustly read an int from the console
     * Re-prompts on invalid input instead of crashing
     */
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
}

