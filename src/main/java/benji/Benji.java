/**
 * Starts the BENJI chatbot application.
 */
package benji;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Processes user commands and manages the user's task list.
 *
 * <p>The {@link #getResponse(String)} method allows both the console UI and a future GUI
 * to use the same chatbot logic.</p>
 */
public class Benji {
    private final TaskList tasks;

    /** Creates BENJI and loads tasks saved from earlier sessions. */
    public Benji() {
        tasks = new TaskList(Storage.loadTasks());
    }

    /**
     * Starts BENJI in the console.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Benji benji = new Benji();
        ui.showWelcome();

        while (true) {
            String userInput = ui.readCommand();
            System.out.println(benji.getResponse(userInput));

            if (Parser.getCommand(userInput) == Command.BYE) {
                break;
            }
        }
    }

    /**
     * Processes one command and returns BENJI's reply.
     *
     * @param userInput command entered by the user
     * @return BENJI's response to the command
     */
    public String getResponse(String userInput) {
        Command command = Parser.getCommand(userInput);

        try {
            switch (command) {
                case BYE:
                    return "Bye. Hope to see you again soon!";
                case LIST:
                    return listTasks();
                case MARK:
                    return updateTaskStatus(userInput, true);
                case UNMARK:
                    return updateTaskStatus(userInput, false);
                case TODO:
                    return addTodo(userInput);
                case DEADLINE:
                    return addDeadline(userInput);
                case EVENT:
                    return addEvent(userInput);
                case DELETE:
                    return deleteTask(userInput);
                case FIND:
                    return findTasks(userInput);
                default:
                    throw new BenjiException("I beg your pardon, I am afraid I do not recognise this command.");
            }
        } catch (BenjiException e) {
            return e.getMessage();
        }
    }

    /** Returns a numbered list of all tasks. */
    private String listTasks() {
        StringBuilder reply = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            reply.append("\n").append(i + 1).append(".").append(tasks.get(i));
        }
        return reply.toString();
    }

    /** Marks or unmarks a task and saves the updated list. */
    private String updateTaskStatus(String userInput, boolean isDone) throws BenjiException {
        String commandWord = isDone ? "mark" : "unmark";
        try {
            int taskNumber = Integer.parseInt(userInput.substring(commandWord.length()).trim());
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new BenjiException("I do apologize, but this task number appears to be non-existent.");
            }

            Task task = tasks.get(taskNumber - 1);
            if (isDone) {
                task.markAsDone();
                Storage.saveTasks(tasks);
                return "Splendid! I have marked this task as completed:\n  " + task;
            }

            task.markAsNotDone();
            Storage.saveTasks(tasks);
            return "OK, I've marked this task as not done yet:\n  " + task;
        } catch (NumberFormatException e) {
            throw new BenjiException("Please enter a whole task number after " + commandWord + ".");
        }
    }

    /** Adds a todo task and saves the updated list. */
    private String addTodo(String userInput) throws BenjiException {
        String description = userInput.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new BenjiException("Please enter a description after todo");
        }

        Task task = new Todo(description);
        tasks.add(task);
        Storage.saveTasks(tasks);
        return "Got it. I've added this task:\n  " + task + "\nNow you have "
                + tasks.size() + " tasks in the list.";
    }

    /** Adds a deadline task and saves the updated list. */
    private String addDeadline(String userInput) throws BenjiException {
        String taskDescription = userInput.substring("deadline".length()).trim();
        int byIndex = taskDescription.indexOf("/by");
        if (byIndex == -1) {
            throw new BenjiException("Please ensure '/by TIME' is included in your deadline description.");
        }

        String description = taskDescription.substring(0, byIndex).trim();
        if (description.isEmpty()) {
            throw new BenjiException("Please enter a description after deadline");
        }

        String by = taskDescription.substring(byIndex + "/by".length()).trim();
        if (by.isEmpty()) {
            throw new BenjiException("Please enter the timing after /by");
        }

        try {
            Task task = new Deadline(description, LocalDate.parse(by));
            tasks.add(task);
            Storage.saveTasks(tasks);
            return "Got it. I've added this task:\n  " + task + "\nNow you have "
                    + tasks.size() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new BenjiException("Please enter the date in yyyy-MM-dd format.");
        }
    }

    /** Adds an event task and saves the updated list. */
    private String addEvent(String userInput) throws BenjiException {
        String taskDescription = userInput.substring("event".length()).trim();
        int startIndex = taskDescription.indexOf("/from");
        int endIndex = taskDescription.indexOf("/to");
        if (startIndex == -1 || endIndex == -1) {
            throw new BenjiException("Please ensure both '/from' and '/to' are included"
                    + " in your event description.");
        }

        String description = taskDescription.substring(0, startIndex).trim();
        if (description.isEmpty()) {
            throw new BenjiException("Please enter a description after event");
        }

        String start = taskDescription.substring(startIndex + "/from".length(), endIndex).trim();
        if (start.isEmpty()) {
            throw new BenjiException("Please enter start timing after /from");
        }

        String end = taskDescription.substring(endIndex + "/to".length()).trim();
        if (end.isEmpty()) {
            throw new BenjiException("Please enter end timing after /to");
        }

        Task task = new Event(description, start, end);
        tasks.add(task);
        Storage.saveTasks(tasks);
        return "Got it. I've added this task:\n  " + task + "\nNow you have "
                + tasks.size() + " tasks in the list.";
    }

    /** Deletes a task and saves the updated list. */
    private String deleteTask(String userInput) throws BenjiException {
        try {
            String taskNumberText = userInput.substring("delete".length()).trim();
            if (taskNumberText.isEmpty()) {
                throw new BenjiException("Please enter a task number after delete");
            }

            int taskNumber = Integer.parseInt(taskNumberText);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new BenjiException("I do apologize, but this task number appears to be non-existent.");
            }

            Task deletedTask = tasks.delete(taskNumber - 1);
            Storage.saveTasks(tasks);
            return "Noted. I've removed this task:\n  " + deletedTask + "\nNow you have "
                    + tasks.size() + " tasks in the list.";
        } catch (NumberFormatException e) {
            throw new BenjiException("Please enter a whole task number after delete.");
        }
    }

    /** Finds tasks whose display text contains the supplied keyword. */
    private String findTasks(String userInput) throws BenjiException {
        String keyword = userInput.substring("find".length()).trim();
        if (keyword.isEmpty()) {
            throw new BenjiException("Please enter a keyword after find.");
        }

        StringBuilder reply = new StringBuilder("Here are the matching tasks in your list:");
        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                counter++;
                reply.append("\n").append(counter).append(".").append(tasks.get(i));
            }
        }
        return reply.toString();
    }
}
