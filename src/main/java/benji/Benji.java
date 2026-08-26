/**
 * Starts the BENJI chatbot application.
 */
package benji;
import benji.Deadline;
import benji.Parser;
import benji.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Runs the BENJI chatbot application.
 *
 * <p>The application reads commands from the user, process them, and manages the user's task list.</p>
 */
public class Benji {
    /**
     * Starts the Benji chatbot and process commands by entered by the user.
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {

//        String line = "____________________________________________________________";
//        String banner = ""
//                + "  BBBBB   EEEEEEE  NN   NN  JJJJJJJ  IIIIIII\n"
//                + "  BB  BB  EE       NNN  NN     JJJ     III  \n"
//                + "  BBBBB   EEEEE    NN N NN     JJJ     III  \n"
//                + "  BB  BB  EE       NN  NNN  JJ JJJ     III  \n"
//                + "  BBBBB   EEEEEEE  NN   NN   JJJJJ   IIIIIII\n";
//
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<benji.Task> tasks = benji.Storage.loadTasks(); // to store all tasks
//
//        System.out.println(line);
//        System.out.println(banner);
//        System.out.println("Hello! I'm BENJI.");
//        System.out.println("What can I do for you?");
//        System.out.println(line);
        Ui ui = new Ui();
        ui.showWelcome();
        TaskList tasks = new TaskList(Storage.loadTasks());


        while (true) {
            String userInput = ui.readCommand();  // waits for a user to type a full line of text and press Enter.
            Command command = Parser.getCommand(userInput);// map to enum command
            if (command == Command.BYE) {
                break;
            }
            try {
                switch (command) {
                    case LIST: {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) { // revealing all the tasks in the tasks array
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;
                    }

                    case MARK: {
                        try {
                            int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                            if (taskNumber < 1 || taskNumber > tasks.size()) { // handle error for numbers that are out of range
                                throw new BenjiException("I do apologize, but this task number appears to be non-existent.");
                            }
                            Task task  = tasks.get(taskNumber - 1);
                            task.markAsDone();
                            Storage.saveTasks(tasks);
                            System.out.println("Splendid! I have marked this task as completed:");
                            System.out.println("  " + task);
                        } catch (NumberFormatException e) {
                            throw new BenjiException("Please enter a whole task number after mark.");
                        }
                        break;
                    }
                    case UNMARK: {
                        int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                        Task task = tasks.get(taskNumber - 1);
                        task.markAsNotDone();
                        Storage.saveTasks(tasks);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + task);
                        break;
                    }
                    case TODO: {
                        String taskDescription = userInput.substring("todo".length()).trim(); // filter todo
                        if (taskDescription.isEmpty()) {
                            throw new BenjiException("Please enter a description after todo");
                        }
                        Task task  = new Todo(taskDescription); // create todo task
                        tasks.add(task);
                        Storage.saveTasks(tasks);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;
                    }
                    case DEADLINE: {
                        String taskDescription = userInput.substring("deadline".length()).trim(); // filer deadline
                        // .indexOf(...) finds the start index of the phrase in the string
                        int byIndex = taskDescription.indexOf("/by");

                        if (byIndex == -1) {
                            throw new BenjiException("Please ensure '/by TIME' is included in your deadline description.");
                        }
                        String description = taskDescription.substring(0, byIndex).trim(); // extract out description
                        if (description.isEmpty()) {
                            throw new BenjiException("Please enter a description after deadline");
                        }

                        // extract out timing
                        String by = taskDescription.substring(byIndex + "/by".length()).trim();
                        if (by.isEmpty()) {
                            throw new BenjiException("Please enter the timing after /by");
                        }


                        try {
                            // convert a piece of text (a String) into a real date object
                            LocalDate byDate = LocalDate.parse(by);

                            Task task = new Deadline(description, byDate);// create deadline task
                            tasks.add(task);
                            Storage.saveTasks(tasks);

                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + task);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                        } catch (DateTimeParseException e) {
                            throw new BenjiException(
                                    "Please enter the date in yyyy-MM-dd format.");
                        }

                        break;

                    }


                    case EVENT: {
                        String taskDescription = userInput.substring("event".length()).trim();
                        int startIndex = taskDescription.indexOf("/from"); // get starting index of "/from"
                        int endIndex = taskDescription.indexOf("/to");  //  get starting index of "/to"
                        if (startIndex == -1 || endIndex == -1) { // check that both /from and /to are in the command
                            throw new BenjiException("Please ensure both '/from' and '/to' are included" +
                                    "in your event description.");
                        }
                        String description = taskDescription.substring(0, startIndex).trim(); // extract task description

                        if (description.isEmpty()) {
                            throw new BenjiException("Please enter a description after event");
                        }

                        // extract start date
                        String start = taskDescription.substring(startIndex + "/from".length(), endIndex).trim();
                        if (start.isEmpty()) {
                            throw new BenjiException("Please enter start timing after /from");
                        }

                        // extract end date
                        String end = taskDescription.substring(endIndex + "/to".length()).trim();
                        if (end.isEmpty()) {
                            throw new BenjiException("Please enter end timing after /to");
                        }

                        Task task = new Event(description, start, end); // create new benji.Event
                        tasks.add(task);
                        Storage.saveTasks(tasks);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
                        break;
                    }


                    case DELETE: {
                        try {
                            String taskDescription = userInput.substring("delete".length()).trim();
                            if (taskDescription.isEmpty()) {
                                throw new BenjiException("Please enter a task number after delete");
                            }
                            int taskNumber = Integer.parseInt(taskDescription.trim());
                            if (taskNumber < 1 || taskNumber > tasks.size()) { // check for invalid task number
                                throw new BenjiException("I do apologize, but this task number appears to " +
                                        "be non-existent.");
                            }
                            Task deleted_task  = tasks.delete(taskNumber - 1); // get deleted task
                            Storage.saveTasks(tasks);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + deleted_task);
                            System.out.println("Now you have " + tasks.size() +  " tasks in the list.");

                        } catch (NumberFormatException e) {
                            // NumberFormatException (commonly referred to by your query) is a runtime error thrown when
                            // code tries to convert a text string into a number, but the string has an invalid format.
                            throw new BenjiException("Please enter a whole task number after delete.");
                        }
                        break;
                    }
                    case UNKNOWN:
                        throw new BenjiException("I beg your pardon, I am afraid I do not recognise this command.");

                }
            } catch (BenjiException e) {
                ui.showError(e.getMessage());
            }



        }

        System.out.println("Bye. Hope to see you again soon!");
        ui.showLine();



    }




}
