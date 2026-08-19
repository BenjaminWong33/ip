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
        String[] tasks = new String[100]; // to store all tasks
        int idx = 0;  // index for tasks


        System.out.println(line);
        System.out.println(banner);
        System.out.println("Hello! I'm BENJI");
        System.out.println("What can I do for you?");
        System.out.println(line);
        while(true) {
            String userInput = scanner.nextLine(); // waits for a user to type a full line of text and press Enter.
            if (userInput.toUpperCase().equals("BYE")) {
                break;
            }

            if (userInput.toUpperCase().equals("LIST")) {
                for (int i = 0; i < idx; i++) { // revealing all the tasks in the tasks array
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[idx] = userInput; // adding a new task
                idx++;
                System.out.println("Added: " + userInput);

            }


            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);



    }
}
