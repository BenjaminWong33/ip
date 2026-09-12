package benji;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Controls BENJI's main application window.
 */
public class MainWindow {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private StackPane headerPane;
    @FXML
    private AnchorPane footerPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button helpButton;
    @FXML
    private Button sendButton;

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
        configureResponsiveControls();
        addDialogBoxes(DialogBox.getBenjiDialog(
                "Hello! I am BENJI. What can I do for you?",
                benjiImage));


        dialogContainer.heightProperty().addListener(
                observable -> scrollPane.setVvalue(1.0));
    }

    /** Configures controls to scale within readable size limits as the window resizes. */
    private void configureResponsiveControls() {
        NumberBinding headerHeight = Bindings.max(60.0,
                Bindings.min(90.0, mainPane.widthProperty().multiply(0.10)));

        NumberBinding footerHeight = Bindings.max(60.0,
                Bindings.min(120.0, mainPane.widthProperty().multiply(0.15)));

        NumberBinding fontSize = Bindings.max(12.0,
                Bindings.min(24.0, mainPane.widthProperty().divide(33.0)));

        NumberBinding sendButtonWidth = Bindings.max(60.0,
                Bindings.min(120.0, mainPane.widthProperty().multiply(0.15)));
        NumberBinding controlPadding = Bindings.max(12.0,
                Bindings.min(24.0, mainPane.widthProperty().multiply(0.03)));

        headerPane.prefHeightProperty().bind(headerHeight);
        footerPane.prefHeightProperty().bind(footerHeight);
        ObjectBinding<Font> responsiveFont =
                Bindings.createObjectBinding(() -> Font.font(fontSize.doubleValue()), fontSize);
        ObjectBinding<Insets> buttonPadding =
                Bindings.createObjectBinding(() -> new Insets(
                        controlPadding.doubleValue() / 2,
                        controlPadding.doubleValue(),
                        controlPadding.doubleValue() / 2,
                        controlPadding.doubleValue()), controlPadding);
        helpButton.fontProperty().bind(responsiveFont);
        helpButton.paddingProperty().bind(buttonPadding);
        userInput.fontProperty().bind(responsiveFont);
        sendButton.fontProperty().bind(responsiveFont);
        sendButton.paddingProperty().bind(buttonPadding);
        sendButton.prefWidthProperty().bind(sendButtonWidth);

        updateScrollPaneAnchors(headerHeight.doubleValue(), footerHeight.doubleValue());
        updateFooterControlAnchors(sendButtonWidth.doubleValue(), controlPadding.doubleValue());
        headerHeight.addListener((observable, oldValue, newValue) ->
                updateScrollPaneAnchors(headerHeight.doubleValue(), footerHeight.doubleValue()));
        footerHeight.addListener((observable, oldValue, newValue) ->
                updateScrollPaneAnchors(headerHeight.doubleValue(), footerHeight.doubleValue()));
        sendButtonWidth.addListener((observable, oldValue, newValue) ->
                updateFooterControlAnchors(newValue.doubleValue(), controlPadding.doubleValue()));
        controlPadding.addListener((observable, oldValue, newValue) ->
                updateFooterControlAnchors(sendButtonWidth.doubleValue(), newValue.doubleValue()));
    }

    /** Keeps the conversation area between the responsive header and footer. */
    private void updateScrollPaneAnchors(double headerHeight, double footerHeight) {
        AnchorPane.setTopAnchor(scrollPane, headerHeight);
        AnchorPane.setBottomAnchor(scrollPane, footerHeight);
    }

    /** Keeps the input controls equal in height and separated as the footer scales. */
    private void updateFooterControlAnchors(double sendButtonWidth, double controlPadding) {
        AnchorPane.setTopAnchor(userInput, controlPadding);
        AnchorPane.setLeftAnchor(userInput, controlPadding);
        AnchorPane.setRightAnchor(userInput, sendButtonWidth + controlPadding * 2);
        AnchorPane.setBottomAnchor(userInput, controlPadding);
        AnchorPane.setTopAnchor(sendButton, controlPadding);
        AnchorPane.setRightAnchor(sendButton, controlPadding);
        AnchorPane.setBottomAnchor(sendButton, controlPadding);
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
