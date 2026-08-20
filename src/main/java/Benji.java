/**
 * Starts the BENJI chatbot application.
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Benji {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String banner = ""
                + "  BBBBB   EEEEEEE  NN   NN  JJJJJJJ  IIIIIII\n"
                + "  BB  BB  EE       NNN  NN     JJJ     III  \n"
                + "  BBBBB   EEEEE    NN N NN     JJJ     III  \n"
                + "  BB  BB  EE       NN  NNN  JJ JJJ     III  \n"
                + "  BBBBB   EEEEEEE  NN   NN   JJJJJ   IIIIIII\n";

        Scanner scanner = new Scanner(System.in); // create a tool or scanner that reads input typed by a user
        ArrayList<Task> tasks = new ArrayList<>(); // to store all tasks

        System.out.println(line);
        System.out.println(banner);
        System.out.println("Hello! I'm BENJI.");
        System.out.println("What can I do for you?");
        System.out.println(line);
        while(true) {
            String userInput = scanner.nextLine(); // waits for a user to type a full line of text and press Enter.
            if (userInput.toUpperCase().equals("BYE")) {
                break;
            }
            try {
                if (userInput.toUpperCase().equals("LIST")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) { // revealing all the tasks in the tasks array
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }

                } else if (userInput.toUpperCase().startsWith("MARK ")) { // startsWith function to track command
                    // Integer.parseInt(...) converts the cleaned text string representing a number into an actual int
                    // .substring(5) skips the first 5 chars, 0 through 4
                    // .trim() cleans up and removes empty space chars from both the front and back
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                        if (taskNumber < 1 || taskNumber > tasks.size()) { // handle error for numbers that are out of range
                            throw new BenjiException("I do apologize, but this task number appears to be non-existent.");
                        }
                        Task task  = tasks.get(taskNumber - 1);
                        task.markAsDone();
                        System.out.println("Splendid! I have marked this task as completed:");
                        System.out.println("  " + task);
                    } catch (NumberFormatException e) {
                        throw new BenjiException("Please enter a whole task number after mark.");
                    }

                } else if (userInput.toUpperCase().startsWith("UNMARK ")) {
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task);

                } else if (userInput.toUpperCase().startsWith("TODO")) {
                    String taskDescription = userInput.substring("todo".length()).trim(); // filter todo
                    if (taskDescription.isEmpty()) {
                        throw new BenjiException("Please enter a description after todo");
                    }
                    Task task  = new Todo(taskDescription); // create todo task
                    tasks.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.toUpperCase().startsWith("DEADLINE")) {
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

                    Task task = new Deadline(description, by); // create deadline task
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() +  " tasks in the list.");

                } else if (userInput.toUpperCase().startsWith("EVENT")) {
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

                    Task task = new Event(description, start, end); // create new Event
                    tasks.add(task);


                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
                } else if (userInput.toUpperCase().startsWith("DELETE")) {
                    try {
                        String taskDescription = userInput.substring("delete".length()).trim();
                        if (taskDescription.isEmpty()) {
                            throw new BenjiException("Please enter a task number after delete");
                        }
                        int taskNumber = Integer.parseInt(taskDescription);
                        Task deleted_task  = tasks.get(taskNumber - 1);
                        tasks.remove(taskNumber);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + deleted_task);
                        System.out.println("Now you have " + tasks.size() +  " tasks in the list.");

                    } catch (NumberFormatException e) {
                        // NumberFormatException (commonly referred to by your query) is a runtime error thrown when
                        // code tries to convert a text string into a number, but the string has an invalid format.
                        throw new BenjiException("Please enter a whole task number after delete.");
                    }

                } else {
//                    tasks[idx] = new Task(userInput); // adding a new task
//                    idx++;
//                    System.out.println("Added: " + userInput);
                      throw new BenjiException("I beg your pardon, I am afraid I do not recognise this command.");
                }
            } catch (BenjiException e) {
                System.out.println(e.getMessage());
            }



            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);



    }
}
