package benji;

/**
 * Represents Event, a subclass of the Task class
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a deadline task with a description, start date, and end date.
     * @param description description of the task
     * @param start description of the start date of the task
     * @param end description of the end date of the task
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
