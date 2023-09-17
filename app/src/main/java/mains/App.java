package mains;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;
import presentation.ConfirmationDialog;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;

public class App extends Application {
    public void start(Stage stage){
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/views/homePage.fxml"));
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Naval Battle");
            stage.getIcons().add(new Image("/assets/images/icon_round.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setOnCloseRequest(event -> {
                event.consume();
                exitGame(stage);
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ferme la fenêtre du jeu si la boîte de dialogue de confirmation est acceptée.
     * via ALT+F4
     * 
     * @param stage La fenêtre du jeu à fermer.
     */
    public void exitGame(Stage stage){
        if (ConfirmationDialog.showConfirmationDialog()) {
            stage.close();
        }
    }

    /**
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
}
