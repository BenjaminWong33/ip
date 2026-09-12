package benji;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controls BENJI's main application window.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Benji benji;

    private final Image userImage = new Image(
            getClass().getResourceAsStream("/images/user.png"));
    private final Image benjiImage = new Image(
            getClass().getResourceAsStream("/images/benji.png"));


    /**
     * Runs after JavaFx has connected the FXML controls to this controller.
     */
    @FXML
    public void initialize() {
        addDialogBoxes(DialogBox.getBenjiDialog(
                "Hello! I am BENJI. What can I do for you?",
                benjiImage));


        dialogContainer.heightProperty().addListener(
                observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Connects this window to the shared BENJI command processor.
     *
     * @param benji command processor used to respond to user input
     */
    public void setBenji(Benji benji) {
        this.benji = benji;
    }

    /** Adds one or more chat messages to the conversation area. */
    private void addDialogBoxes(DialogBox...dialogBoxes) {
        dialogContainer.getChildren().addAll(dialogBoxes);
    }

    /**
     * Sends the user's command to BENJI and shows both chat messages.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText().trim();

        if (userText.isEmpty()) {
            return;
        }

        if (Parser.getCommand(userText) == Command.HELP) {
            addDialogBoxes(DialogBox.getUserDialog(userText, userImage));
            userInput.clear();
            showHelp();
            return;
        }
        String benjiText = benji.getResponse(userText);

        addDialogBoxes(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBenjiDialog(benjiText, benjiImage));

        userInput.clear();
    }

    /**
     * Shows a guide explaining the commands that BENJI understands.
     */
    @FXML
    private void showHelp() {
        Alert helpDialog = new Alert(Alert.AlertType.INFORMATION);
        helpDialog.setTitle("BENJI Command Guide");
        helpDialog.setHeaderText("How to use BENJI");

        helpDialog.setContentText(
                "list\n"
                        + "  Show every task.\n\n"
                        + "todo DESCRIPTION\n"
                        + "  Add a to-do task.\n\n"
                        + "deadline DESCRIPTION /by yyyy-MM-dd\n"
                        + "  Add a task with a deadline.\n\n"
                        + "event DESCRIPTION /from START /to END\n"
                        + "  Add an event.\n\n"
                        + "mark TASK_NUMBER\n"
                        + "unmark TASK_NUMBER\n"
                        + "  Change a task's completion status.\n\n"
                        + "delete TASK_NUMBER\n"
                        + "  Remove a task.\n\n"
                        + "find KEYWORD\n"
                        + "  Search for tasks.\n\n"
                        + "help\n"
                        + "  Show user guide\n\n"
                        + "bye\n"
                        + "  Exit BENJI.");

        helpDialog.getButtonTypes().setAll(ButtonType.CLOSE);
        helpDialog.showAndWait();
    }
}
