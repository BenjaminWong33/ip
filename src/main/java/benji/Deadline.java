package benji;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Deadline, a subclass of the Task class.
 */
public class Deadline extends Task {
    protected LocalDate by;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a deadline task with a description and deadline date.
     * @param description description of the task.
     * @param by description of the date bby which the task should be completed.
     */
    public Deadline(String description, LocalDate by) {
        this.by = by;
        super(description);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }
}
