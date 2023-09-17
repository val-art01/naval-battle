package controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import presentation.AIPlayer;
import presentation.ButtonNavigationManagement;

public class AIController extends BaseController {

    @FXML private Button backButton;
    @FXML private CheckBox cheatBox;

    private AIPlayer ai;

    @Override
    protected int getNumberOfShips() {
        return 5;
    }

    @Override
    protected List<Integer> getShipLengths() {
        return Arrays.asList(5, 4, 3, 3, 2);
    }

    @Override
    protected int getGridType() {
        return 2;
    }

    @Override
    protected Color getShipColor() {
        // Define AI-specific ship color
        return Color.TRANSPARENT;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        ai = new AIPlayer(manhattanDistanceLabel, closestShipLengthLabel);
    }

    @FXML
    void homePage(ActionEvent event) {
        new ButtonNavigationManagement(event, "homePage.fxml"); 
    }

    @FXML
    void pullSelectionBox(ActionEvent event) {
        ai.performAIShotAndMove(grid, gridPane);
    }

    @FXML
    void EndGame(ActionEvent event) {

    }

    @FXML
    void cheatModus(ActionEvent event) {
        if (cheatBox.isSelected()) {
            grid.markOccupiedCellsOnGrid(gridPane, Color.BLACK);
        } else {
           grid.markOccupiedCellsOnGrid(gridPane, getShipColor());
        }
    }

}
