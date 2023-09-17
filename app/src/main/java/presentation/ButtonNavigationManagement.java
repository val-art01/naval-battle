package presentation;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class ButtonNavigationManagement {
    private String pageName;
    private Stage stage;
    private Scene scene;
    private ActionEvent event;
    
    public ButtonNavigationManagement(ActionEvent event, String pageName){
        this.event = event;

        if (pageName.equals("configurationPage.fxml")) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/views/"+pageName));
                scene = new Scene(root);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();            
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException ex) {
                // Logger.getLogger(ButtonNavigationManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.pageName = pageName;
            this.navigationPage();
        }
    }

    private void navigationPage() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/"+this.pageName));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            // Logger.getLogger(ButtonNavigationManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
