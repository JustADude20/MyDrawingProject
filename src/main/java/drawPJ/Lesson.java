package drawPJ;

/**
 * Create a structure that focuses on teaching drawing
 * Try to keep the lessons light and include: title ,level ,short summary and completed flag
 * The Main class uses this to implement the "Lesson Completed?" logic from the activity diagram
 */
public class Lesson {
    private final int id;
    private final String title;
    private final String level;
    private final String summary;
    private boolean completed;

    public Lesson(int id, String title, String level, String summary) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.summary = summary;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() { return title; }

    public String getLevel() { return level; }

    public String getSummary() { return summary; }

    public boolean isCompleted() { return completed; }

    public void complete() { completed = true; }

    /**
     * Returns a simple "practice assignment" text for the lesson
     * This could include custom lessons if desired for future improvements
     */
    public String getPracticeTask() {
        return "Practice: " + title + " — draw for 10 minutes.";
    }

    @Override
    public String toString() {
        return id + ". " + title + " (" + level + ")" +
                (completed ? " [COMPLETED]" : "");
    }
}
