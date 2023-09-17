package controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import presentation.ButtonNavigationManagement;
import presentation.ConfirmationDialog;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
public class HomeController {

    @FXML private AnchorPane anchorPane;
    @FXML private BorderPane borderPane;
    @FXML private Button exitButton;
    @FXML private Button humanModeButton;
    @FXML private Button iaModeButton;
    @FXML private Button optionButton;
    @FXML private ImageView closeWindow;

    Stage stage;
    
    @FXML
    /**
     * Ferme la fenetre du jeu si la boîte de dialogue de confirmation est acceptee.
     * via le bouton Quitter
     * 
     * @param stage La fenêtre du jeu à fermer.
     */
    public void exitGame(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        if (ConfirmationDialog.showConfirmationDialog()) {
            stage.close();
        }
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        if (ConfirmationDialog.showConfirmationDialog()) {
            Platform.exit();
        }        
    }

    @FXML
    private void modelPageAI(ActionEvent event) {
        new ButtonNavigationManagement(event, "aiPage.fxml");
    }

    @FXML
    private void modelPageHuman(ActionEvent event) {
        new ButtonNavigationManagement(event, "fightPage.fxml");
    }

    @FXML
    private void optionPage(ActionEvent event) {

        // Parent root;
        //     try {
        //         root = FXMLLoader.load(getClass().getResource("/views/configurationPage.fxml"));
        //         Scene scene = new Scene(root);
        //         stage = (Stage)((Node)event.getSource()).getScene().getWindow();            
        //         stage.setTitle("Fenêtre contextuelle");
        //         // votre code pour invoquer la méthode generate
        //         stage.initModality(Modality.APPLICATION_MODAL);
                
        //         stage.setScene(scene);
        //         stage.showAndWait();
        //     }catch (IOException ex) {
        //         ex.printStackTrace();
        //     }

        new ButtonNavigationManagement(event, ""); 
    }
}
