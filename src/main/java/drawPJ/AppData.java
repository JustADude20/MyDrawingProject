package drawPJ;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * AppData simulates a tiny in-memory database.
 * Responsibilities: Hold the list of prompts ,hold the list of lessons and provide lookup methods for the rest of the app
 * Keeping this logic in one place and try to keep the main code cleaner
 */
// TODO: Persist these prompts/lessons to a JSON or text file instead of hard-coding
public class AppData {

    private final List<Prompt> prompts = new ArrayList<>();
    private final List<Lesson> lessons = new ArrayList<>();

    public AppData() {
        seedPrompts();
        seedLessons();
    }

    /**
     * Hard-coded prompts for now but I would like to add a sample image feature.
     */
    private void seedPrompts() {
        prompts.add(new Prompt(1, "Draw a leaf with detailed veins.", LocalDate.now()));
        prompts.add(new Prompt(2, "Sketch your favorite mug.", LocalDate.now().minusDays(1)));
        prompts.add(new Prompt(3, "Draw a simple self-portrait.", LocalDate.now().minusDays(2)));
    }

    /**
     * Basic "beginner" lessons
     */
    private void seedLessons() {
        lessons.add(new Lesson(1, "Basic Shapes", "Beginner",
                "Learn to draw circles, squares, and triangles."));
        lessons.add(new Lesson(2, "Light and Shadow", "Beginner",
                "Learn simple shading techniques."));
        lessons.add(new Lesson(3, "Simple Faces", "Beginner",
                "Learn proportions for the eyes, nose, and mouth."));
    }

    /**
     * Returns what we consider "today's" prompt
     * Currently this is always the first prompt in the list
     * TODO: Make this date-aware and rotate prompts
     */
    public Prompt getTodayPrompt() {
        return prompts.get(0);
    }

    public List<Prompt> getPrompts() {
        return prompts;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Lookup helper used by the menu when a user chooses a lesson by ID
     */
    public Lesson findLessonById(int id) {
        return lessons.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
