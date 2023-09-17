package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import models.FleetShips;
import models.Grid;

public abstract class BaseController implements Initializable {

    @FXML protected GridPane gridPane;
    @FXML protected Label closestShipLengthLabel;
    @FXML protected Label manhattanDistanceLabel;

    protected static final int ALPHABET_SIZE = 26;
    protected static final int START_INDEX = (int) 'A';
    protected Grid grid;

    protected abstract int  getNumberOfShips();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int row = 10;
        int column = 10;
        grid = new Grid(row, column);
        grid.createGridWithGraphics(gridPane, getGridType());

        // Afficher les en-tetes de colonne (Chiffres)
        for (int col = 0; col < column; col++) {
            Label columnHeader = new Label(String.valueOf(col + 1));
            columnHeader.getStyleClass().add("grid_label");
            columnHeader.setPrefSize(35, 35);
            gridPane.add(columnHeader, col + 1, 0);
        }

        // Afficher les en-tetes de ligne (lettres)
        for (int i = 0; i < row; i++) {
            Label rowHeader = new Label(String.valueOf((char) (START_INDEX + (i % ALPHABET_SIZE))));
            rowHeader.getStyleClass().add("grid_label");
            rowHeader.setPrefSize(35, 35);
            gridPane.add(rowHeader, 0, i + 1);
        }

        FleetShips fleet = new FleetShips(getNumberOfShips());
        fleet.setShipLengths(getShipLengths());
        fleet.ShipPlacer(grid, gridPane, getShipColor());
    }

    protected abstract int getGridType();
    protected abstract List<Integer> getShipLengths();
    protected abstract Color getShipColor();
}
