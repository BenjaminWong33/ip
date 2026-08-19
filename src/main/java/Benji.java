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
        System.out.println(line);
        System.out.println(banner);
        System.out.println("H#ello! I'm BENJI");
        System.out.println("What can I do for you?");
        System.out.println(line);
        while(true) {
            String userInput = scanner.nextLine(); // waits for a user to type a full line of text and press Enter.
            if (userInput.toUpperCase().equals("BYE")) {
                break;
            }
            System.out.println(userInput);
            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);



    }
}
