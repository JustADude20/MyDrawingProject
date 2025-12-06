package drawPJ;

import java.time.LocalDate;

/**
 * Represents a single sketch created by the user in response to a Prompt
 * Note: The image file functionality has not yet been implemented, but for the moment I will focus on trying to create something like a journal known as "200 drawing prompts" that contains the following:
 *  -which prompt it belongs to
 *  -when it was created
 *  -any notes the user wrote about it
 */
// TODO: Later improvements will include adding a feature that allows linking with applications like Krita, where the user can upload digital drawings
public class Sketch {
    private final int id;
    private final Prompt prompt;
    private final LocalDate createdDate;
    private final String notes;

    public Sketch(int id, Prompt prompt, LocalDate createdDate, String notes) {
        this.id = id;
        this.prompt = prompt;
        this.createdDate = createdDate;
        this.notes = notes == null ? "" : notes;
    }

    public int getId() {
        return id;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Sketch #" + id +
                " | Date: " + createdDate +
                " | Prompt: " + prompt.getText() +
                (notes.isBlank() ? "" : " | Notes: " + notes);
    }
}
