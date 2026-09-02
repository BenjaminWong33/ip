package benji;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents one chat message with text and an avatar.
 */
public class DialogBox extends HBox {
    private static final String USER_BUBBLE_STYLE =
            "-fx-background-color: #D9ECFF;"
                    + "-fx-background-radius: 10;"
                    + "-fx-border-radius: 10;";

    private static final String BENJI_BUBBLE_STYLE =
            "-fx-background-color: white;"
                    + "-fx-background-radius: 10;"
                    + "-fx-border-color: #D0D0D0;"
                    + "-fx-border-radius: 10;";

    private final Label message;

    /**
     * Creates a chat message.
     *
     * @param text message to display
     * @param image avatar for the speaker
     */
    private DialogBox(String text, Image image) {
        message = new Label(text);
        message.setWrapText(true);
        message.setMaxWidth(270);
        message.setPadding(new Insets(10, 14, 10, 14));
        message.setStyle(USER_BUBBLE_STYLE);

        ImageView avatar = new ImageView(image);
        avatar.setFitHeight(50);
        avatar.setFitWidth(50);
        avatar.setPreserveRatio(true);

        setAlignment(Pos.TOP_RIGHT);
        setSpacing(10);
        getChildren().addAll(message, avatar);
    }

    /**
     * Changes a user-style message into a left-aligned BENJI message.
     */
    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);

        setAlignment(Pos.TOP_LEFT);
        message.setStyle(BENJI_BUBBLE_STYLE);
    }

    /**
     * Creates a blue, right-aligned user speech bubble.
     *
     * @param text user message
     * @param image user avatar
     * @return user dialog box
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Creates a white, left-aligned BENJI speech bubble.
     *
     * @param text BENJI message
     * @param image BENJI avatar
     * @return BENJI dialog box
     */
    public static DialogBox getBenjiDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        return dialogBox;
    }
}