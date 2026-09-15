package benji;

/**
 * Represents a task that takes place during a specified time period.
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
        assert start != null && !start.isBlank() : "Event should have a start time";
        assert end != null && !end.isBlank() : "Event should have an end time";
        this.start = start;
        this.end = end;
    }

    /**
     * Returns this event with its start and end times in BENJI's display format.
     *
     * @return the event prefixed with "[E]" and followed by its time range
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
