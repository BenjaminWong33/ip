package benji;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    /**
     * Creates a chat message.
     *
     * @param text message to display
     * @param image avatar for the speaker
     */
    public DialogBox(String text, Image image) {
        Label message = new Label(text);
        message.setWrapText(true);
        message.setMaxWidth(270);

        ImageView avatar = new ImageView(image);
        avatar.setFitHeight(50);
        avatar.setFitWidth(50);
        avatar.setPreserveRatio(true);

        setAlignment(Pos.TOP_RIGHT);
        setSpacing(10);
        getChildren().addAll(message, avatar);

    }

    /**
     * Reverses the avatar and message positions for Benji's messages.
     */
    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    public static DialogBox getBenjiDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        return dialogBox;
    }
}
