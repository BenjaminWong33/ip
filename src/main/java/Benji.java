/**
 * Starts the BENJI chatbot application.
 */
import java.util.Scanner;

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
        Task[] tasks = new Task[100]; // to store all tasks
        int idx = 0;  // index for tasks

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

            if (userInput.toUpperCase().equals("LIST")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < idx; i++) { // revealing all the tasks in the tasks array
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else if (userInput.toUpperCase().startsWith("MARK ")) { // startsWith function to track command
                // Integer.parseInt(...) converts the cleaned text string representing a number into an actual int
                // .substring(5) skips the first 5 chars, 0 through 4
                // .trim() cleans up and removes empty space chars from both the front and back
                int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                Task task  = tasks[taskNumber - 1];
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task);

            } else if (userInput.toUpperCase().startsWith("UNMARK ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                Task task = tasks[taskNumber - 1];
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + task);

            } else if (userInput.toUpperCase().startsWith("TODO")) {
                String taskDescription = userInput.substring("todo".length()).trim(); // filter todo
                Task task  = new Todo(taskDescription); // create todo task
                tasks[idx] = task;
                idx++;
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + task);
                System.out.println("Now you have " + idx + " tasks in the list.");

            } else if (userInput.toUpperCase().startsWith("DEADLINE")) {
                String taskDescription = userInput.substring("deadline".length()).trim(); // filer deadline
                // .indexOf(...) finds the start index of the phrase in the string
                int byIndex = taskDescription.indexOf("/by");
                String description = taskDescription.substring(0, byIndex).trim(); // extract out description
                String by = taskDescription.substring(byIndex + "/by".length()).trim(); // extract out timing
                Task task = new Deadline(description, by); // create deadline task
                tasks[idx] = task;
                idx ++;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + idx +  " tasks in the list.");

            } else if (userInput.toUpperCase().startsWith("EVENT")) {
                String taskDescription = userInput.substring("event".length()).trim();
                int startIndex = taskDescription.indexOf("/from"); // get starting index of "/from"
                int endIndex = taskDescription.indexOf("/to");  //  get starting index of "/to"
                String description = taskDescription.substring(0, startIndex).trim(); // extract task description
                String start = taskDescription.substring(startIndex + "/from".length(), endIndex).trim();  // extract start d
                String end = taskDescription.substring(endIndex + "/to".length()).trim(); // extract end d
                Task task = new Event(description, start, end); // create new Event
                tasks[idx] = task;
                idx++;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + idx +  " tasks in the list.");
            } else {
                tasks[idx] = new Task(userInput); // adding a new task
                idx++;
                System.out.println("Added: " + userInput);

            }


            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);



    }
}
