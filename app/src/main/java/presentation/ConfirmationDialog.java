package presentation;



import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * La classe ConfirmationDialog fournit des méthodes pour afficher des boîtes de dialogue de confirmation 
 */
public class ConfirmationDialog {

    /**
     * Affiche une boîte de dialogue de confirmation pour quitter le jeu.
     *
     * @return {@code true} si le bouton "OK" est cliqué, {@code false} sinon.
     */
    public static boolean showConfirmationDialog() {
        String title = "Quitter";
        String header = "Vous etes sur le point de quitter le jeu !";
        return showDialog(title, header, null);
    }

    /**
     * Affiche une boîte de dialogue de confirmation pour recommencer la partie.
     *
     * @return {@code true} si le bouton "Oui" est cliqué, {@code false} sinon.
     */
    public static boolean replayPartyConfirmationDialog() {
        String title = "Recommencer la partie";
        String header = "Etes-vous sur de vouloir recommencer la partie ?";
        String content = "Toutes les actions en cours seront perdues.";
        return showDialog(title, header, content);
    }

    public static void showEndGameDialog(double score) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin de la partie");
        alert.setHeaderText(null);
        alert.setContentText("La partie est terminee !\n votre Score obtenu est: " + score);
        alert.showAndWait();
        // return showDialog(title, header, content);
    }

    public static void showEndGameDialog(double score, int sunkShipsCount, int totalShots) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin de la partie");
        alert.setHeaderText(null);

        String message = "La partie est terminee !\n";

        double percentage = (double) sunkShipsCount / totalShots;

        if (percentage <= 0.25) {
            message += "Felicitations ! Tu as ete tres efficace !\n";
        } else if (percentage <= 0.5 && percentage > 0.25) {
            message += "Felicitations ! Tu as ete efficace !\n";
        } else {
            message += "Oups Domage! Tu as detruit les navires ennemis, mais tu peux ameliorer ton score !\n";
        }

        message += "Tu as coule " + sunkShipsCount + " en " + totalShots + " tentatives.\nscore : " + score;

        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * Affiche une boîte de dialogue de confirmation avec le titre, l'en-tête et le contenu spécifiés.
     *
     * @param title   Le titre de la boîte de dialogue.
     * @param header  L'en-tête de la boîte de dialogue.
     * @param content Le contenu de la boîte de dialogue.
     * @return {@code true} si le bouton "OK" ou "Oui" est cliqué, {@code false} sinon.
     */
    private static boolean showDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
