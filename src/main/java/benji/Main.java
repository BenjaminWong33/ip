package benji;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Creates the JavaFX application window
 */
public class Main extends Application {
    private final Image userImage = new Image(
            getClass().getResourceAsStream("/images/user.png"));
    private final Image benjiImage = new Image(
            getClass().getResourceAsStream("/images/benji.png"));


    /**
     * Creates and shows the first JavaFX window
     *
     * @param stage the application window supplied by JavaFX
     */
    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        dialogContainer.setSpacing(10);
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        userInput.setPromptText("Type a command here...");

        Button sendButton = new Button("Send");
        DialogBox greeting = new DialogBox(
                "Hello! I am BENJI. What can I do for you?",
                benjiImage);
        dialogContainer.getChildren().add(greeting);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        configureLayout(mainLayout, scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setTitle("BENJI");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the size and position of the controls in the application window.
     */
    private void configureLayout(AnchorPane mainLayout, ScrollPane scrollPane,
                                 TextField userInput, Button sendButton) {
        mainLayout.setPrefSize(400, 600);

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 45.0);

        AnchorPane.setLeftAnchor(userInput, 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);
        AnchorPane.setRightAnchor(userInput, 80.0);

        sendButton.setPrefWidth(70);
        AnchorPane.setRightAnchor(sendButton, 5.0);
        AnchorPane.setBottomAnchor(sendButton, 5.0);
    }
}

