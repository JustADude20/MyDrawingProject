package drawPJ;

import java.time.LocalDate;

/**
 * A drawing prompt that suggests what the user should sketch
 * Currently trying to keep it simple: just an id, the text, and the associated date
 * The "today" prompt is picked by AppData code
 */
public class Prompt {
    private final int id;
    private final String text;
    private final LocalDate date;

    public Prompt(int id, String text, LocalDate date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" + date + "] " + text;
    }
}
