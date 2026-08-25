import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    String line = "____________________________________________________________";
    String banner = ""
            + "  BBBBB   EEEEEEE  NN   NN  JJJJJJJ  IIIIIII\n"
            + "  BB  BB  EE       NNN  NN     JJJ     III  \n"
            + "  BBBBB   EEEEE    NN N NN     JJJ     III  \n"
            + "  BB  BB  EE       NN  NNN  JJ JJJ     III  \n"
            + "  BBBBB   EEEEEEE  NN   NN   JJJJJ   IIIIIII\n";

    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in); // create a tool or scanner that reads input typed by a user
    }

    public void showWelcome() {
        System.out.println(line);
        System.out.println(banner);
        System.out.println("Hello! I'm BENJI.");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
