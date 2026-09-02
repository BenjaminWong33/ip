package benji;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final Benji benji = new Benji();

    @Override
    public void start(Stage stage) {
        try {
            // find the UI FXML file and load it
            FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource("/view/MainWindow.fxml"));

            // load the UI and create a scene
            Scene scene = new Scene(fxmlLoader.load());

            // get the java class controller of the FXML file
            //The <MainWindow> tells Java that the controller is a MainWindow object
            fxmlLoader.<MainWindow>getController().setBenji(benji);

            stage.setTitle("BENJI");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load BENJI's main window.", e);
        }
    }
}


