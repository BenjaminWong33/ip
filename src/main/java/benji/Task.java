package benji;

/**
 * Represents a general task in the BENJI task manager.
 *
 * <p>A task has a description and can be marked as either
 * completed or incomplete.</p>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return "X" if the task is completed, or a blank space otherwise.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return the task's status icon followed by its description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }


}
