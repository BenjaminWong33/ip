package benji;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specified date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate by;

    /**
     * Creates a deadline task with a description and deadline date.
     * @param description description of the task.
     * @param by description of the date bby which the task should be completed.
     */
    public Deadline(String description, LocalDate by) {
        assert by != null : "A deadline cannot exist without a date";
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }
}
