package controllers;

import models.Cell;
import models.Ship;
import presentation.ButtonNavigationManagement;
import presentation.ConfirmationDialog;
import presentation.Score;
import presentation.Shoot;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class FightController extends BaseController {

    @FXML private Label cellsHitLabel;
    @FXML private Label shipsSunkLabel;

    private int totalShots = 0;

    @Override
    protected int getNumberOfShips() {
        return 5;
    }

    @Override
    protected int getGridType() {
        return 1;
    }

    @Override
    protected Color getShipColor() {
        return Color.TRANSPARENT;
    }

    @Override
    protected List<Integer> getShipLengths() {
        return Arrays.asList(5, 4, 3, 3, 2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    @FXML
    public void homePage(ActionEvent event) {
        new ButtonNavigationManagement(event, "homePage.fxml"); 
    }

    @FXML
    public void pullSelectionBox(ActionEvent event) {
        Cell selectedCell = grid.getSelectedCell();

        if (!selectedCell.isHit()) {
            Shoot shoot = new Shoot(grid, selectedCell);
            this.totalShots++;
            // Calculer les informations nécessaires en appelant les méthodes appropriées
            int manhattanDistance = shoot.closestShipLength()[0];
            int closestShipLength = shoot.closestShipLength()[1];
            int hitCellsCount = shoot.cellsCount();
            int sunkShipsCount = shoot.sunkShipsCount();

            // Marquer la cellule sélectionnée comme tirée
            Label cellLabel = grid.getLabelFromCell(selectedCell, gridPane);
            shoot.markCellLabel(cellLabel, selectedCell.isOccupied());    

            // Mettre à jour les labels avec les résultats
            manhattanDistanceLabel.setText( Integer.toString(manhattanDistance) );
            closestShipLengthLabel.setText(Integer.toString(closestShipLength));
            cellsHitLabel.setText(Integer.toString(hitCellsCount) );
            shipsSunkLabel.setText(Integer.toString(sunkShipsCount) );

            if (Ship.allEnemyShipsSunk(grid)) {
                Score score = new Score(grid);                
                ConfirmationDialog.showEndGameDialog(score.calculateScore(), sunkShipsCount, this.totalShots);
            }
        }
    }

    @FXML
    public void replayParty(ActionEvent event) {
        if (ConfirmationDialog.replayPartyConfirmationDialog()) {
            new ButtonNavigationManagement(event, "fightPage.fxml");
        }
    }

    @FXML
    public void saveGame(ActionEvent event) {

    }
}