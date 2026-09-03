package benji;

import javafx.fxml.FXML;
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

    public void setBenji(Benji benji) {
        this.benji = benji;
    }

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

        String benjiText = benji.getResponse(userText);

        addDialogBoxes(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBenjiDialog(benjiText, benjiImage));

        userInput.clear();
    }
}
