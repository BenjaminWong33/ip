package benji;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents one chat message with text and an avatar.
 */
public class DialogBox extends HBox {

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
}
