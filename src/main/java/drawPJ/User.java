package drawPJ;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user of the drawing app.
 * Responsibilities: Track identity info (id, name, email), maintain a daily streak count, hold the user's sketches and track which lessons have been completed
 */
// TODO: Trying to increase data capacity
public class User {
    private final int id;
    private final String name;
    private final String email;
    private int streakCount;

    // Composition: User "has many" sketches.
    private final List<Sketch> sketches = new ArrayList<>();

    // We just store lesson IDs here instead of full Lesson objects
    // to keep coupling low.
    private final List<Integer> completedLessonIds = new ArrayList<>();

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.streakCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getStreakCount() {
        return streakCount;
    }

    /**
     * NOTE: There is no calendar-based reset yet
     * TODO: Add date tracking to reset streak when a day is missed
     */
    public void incrementStreak() {
        streakCount++;
    }

    public void addSketch(Sketch sketch) {
        sketches.add(sketch);
    }

    public List<Sketch> getSketches() {
        return sketches;
    }

    /**
     * Mark a lesson as completed and update the lesson object itself
     */
    public void markLessonCompleted(Lesson lesson) {
        if (!completedLessonIds.contains(lesson.getId())) {
            completedLessonIds.add(lesson.getId());
            lesson.complete();
        }
    }

    public boolean hasCompleted(Lesson lesson) {
        return completedLessonIds.contains(lesson.getId());
    }
}
