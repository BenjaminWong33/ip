package benji;

import java.util.Scanner;

/**
 * Handles interaction between BENJI and the user through the console.
 *
 * <p>This class is responsible for displaying messages and
 * reading commands entered by the user.</p>
 */
public class Ui {
    String line = "____________________________________________________________";
    String banner = ""
            + "  BBBBB   EEEEEEE  NN   NN  JJJJJJJ  IIIIIII\n"
            + "  BB  BB  EE       NNN  NN     JJJ     III  \n"
            + "  BBBBB   EEEEE    NN N NN     JJJ     III  \n"
            + "  BB  BB  EE       NN  NNN  JJ JJJ     III  \n"
            + "  BBBBB   EEEEEEE  NN   NN   JJJJJ   IIIIIII\n";

    private Scanner scanner;

    /**
     * Creates a new user interface and prepares it to read input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in); // create a tool or scanner that reads input typed by a user
    }

    /**
     * Displays the welcome message when BENJI starts
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println(banner);
        System.out.println("Hello! I'm BENJI.");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line in the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
