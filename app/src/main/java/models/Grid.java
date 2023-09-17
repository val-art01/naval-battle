package models;

import java.util.List;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Node;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

 /**
 * La classe Grid représente une grille de jeu composée de cellules. Cette grille est utilisée 
 * pour placer des navales. Chaque grille est définie par un nombre de lignes et de colonnes, 
 * et elle contient une liste de cellules. La classe offre des méthodes pour créer et manipuler 
 * la grille, notamment pour placer des navires et gérer les interactions avec les cellules.
 */

public class Grid {
    private int rows;
    private int columns;
    private List<Cell> cells;
    private Cell selectedCell;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new ArrayList<>();
        this.selectedCell = null;
        // Initialisation des cellules dans la grille
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells.add(new Cell(i, j));
            }
        }
    }

    /**
     * Crée la représentation graphique de la grille et l'ajoute à un conteneur GridPane spécifié.
     *
     * @param gridPane Le conteneur GridPane où ajouter les éléments graphiques de la grille.
     */
    public void createGridWithGraphics(GridPane gridPane, int number) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = getCells().get(i * columns + j);
                Label label = createGridLabel(cell, gridPane, number);
                gridPane.add(label, j + 1, i + 1);
            }
        }
    }

    /**
     * 
     * Crée une étiquette (label) graphique pour représenter une cellule de la grille.
     *
     * @param cell La cellule à représenter graphiquement.
     * @return L'étiquette graphique de la cellule.
     */
    private Label createGridLabel(Cell cell, GridPane gridPane, int number) {
        Label label = new Label();
        label.setPrefSize(35, 35);
        label.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: transparent;");
        if (number == 1) {
            label.setOnMouseClicked(e -> handleGridCellSelection(cell, label, gridPane));
        }        
        return label;
    }

    /**
     * Gère la sélection/désélection d'une cellule de la grille.
     *
     * @param cell La cellule dont la sélection est modifiée.
     */
    private void handleGridCellSelection(Cell cell, Label label, GridPane gridPane) {
        if (selectedCell == null && !cell.isHit()) {
            // Si aucune cellule n'est sélectionnée, sélectionne la cellule actuelle
            cell.setSelected(true);
            label.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: yellow;");
            selectedCell = cell;
            System.out.println("Case sélectionnée : " + (char) ('A' + cell.getRow()) + (cell.getCol() + 1));
        } else if (selectedCell.equals(cell)) {
            // Si la cellule actuelle est déjà sélectionnée, désélectionne-la
            cell.setSelected(false);
            label.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: transparent;");
            selectedCell = null;
            System.out.println("Case désélectionnée : " + (char) ('A' + cell.getRow()) + (cell.getCol() + 1));
        } else {
            // Si une autre cellule est déjà sélectionnée, désélectionne-la et sélectionne
            // la nouvelle cellule
            selectedCell.setSelected(false);
            Label previousLabel = getLabelFromCell(selectedCell, gridPane);
            previousLabel.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: transparent;");
            cell.setSelected(true);
            label.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: yellow;");
            selectedCell = cell;
            System.out.println("Case sélectionnée : " + (char) ('A' + cell.getRow()) + (cell.getCol() + 1));
        }

    }

    /**
     * la methode getLabelFromCell recupere l'étiquette graphique associée à une cellule spécifique
     * dans le GridPane.
     *
     * @param cell La cellule pour laquelle rechercher l'étiquette.
     * @param gridPane Le GridPane dans lequel rechercher l'étiquette.
     * @return L'étiquette associée à la cellule donnée, ou null si aucune étiquette n'est trouvée.
     */

    public Label getLabelFromCell(Cell cell, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                if (GridPane.getColumnIndex(label) == cell.getCol() + 1
                    && GridPane.getRowIndex(label) == cell.getRow() + 1) {
                    return label;
                }
            }
        }
        return null; // Retourne null si le label n'est pas trouvé
    }

     /**
     * la methode generateRandomStartCell Génère une cellule de départ aléatoire pour placer 
     * un navire.
     *
     * @param lengthShip Longueur du navire.
     * @param isHorizontal Indique si le navire est positionné horizontalement.
     * @return La cellule de départ aléatoire pour le navire.
     */

    public Cell generateRandomStartCell(int lengthShip, boolean isHorizontal) {
        int maxCol = columns - 1;
        int maxRow = rows - 1;

        Random random = new Random();
        int randomCol = random.nextInt(maxCol + 1);
        int randomRow = random.nextInt(maxRow + 1);

        Cell startCell = new Cell(randomRow, randomCol);

        if (isHorizontal && (randomCol + lengthShip - 1) <= maxCol) {
            return startCell;
        } else if (!isHorizontal && randomRow + lengthShip - 1 <= maxRow) {
            return startCell;
        } else {
            // Le navire ne peut pas être placé ici, essayez une autre position
            return generateRandomStartCell(lengthShip, isHorizontal);
        }
    }
    

    /**
     * La methode canPlaceShip vérifie si un navire peut être placé sans chevaucher avec 
     * d'autres navires ou sortir des limites.
     *
     * @param ship Le navire à placer.
     * 
     * @return Vrai si le navire peut être placé, sinon faux.
     */   

    public boolean canPlaceShip(Ship ship) {
        Cell startCell = ship.getStart();
        int startRow = startCell.getRow();
        int startCol = startCell.getCol();

        int endRow = startRow + (ship.isHorizontal() ? 0 : ship.getLengthShip() - 1);
        int endCol = startCol + (ship.isHorizontal() ? ship.getLengthShip() - 1 : 0);    
    
        for (int row = startRow - 1; row <= endRow + 1; row++) {
            for (int col = startCol - 1; col <= endCol + 1; col++) {
                if (isValidCell(row, col) && isOccupiedCell(row, col)) {
                    return false; // Au moins une cellule voisine est occupée, ne pas placer le navire
                }
            }
        }        
        // Toutes les cellules voisines sont libres, le navire peut être placé
        return true;
    }
    
   /**
     * isValidCell vérifie si une cellule spécifiée par ses coordonnées de ligne et de colonne 
     * est à l'intérieur de la grille.
     *
     * @param row La coordonnée de ligne de la cellule.
     * @param col La coordonnée de colonne de la cellule.
     * @return {@code true} si la cellule est à l'intérieur de la grille, {@code false} sinon.
     */
    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    /**
     *  isOccupiedCell vérifie si une cellule spécifiée par ses coordonnées de ligne et de colonne
     *  est occupée par un navire.
     *
     * @param row La coordonnée de ligne de la cellule.
     * @param col La coordonnée de colonne de la cellule.
     * @return {@code true} si la cellule est occupée par un navire, {@code false} sinon.
     */    
    private boolean isOccupiedCell(int row, int col) {
        if (isValidCell(row, col)) {
            return cells.get(row * columns + col).isOccupied();
        }
        return false; // Cellule hors de la grille est considérée comme non occupée
    }   

    /**
     *  getValidCell récupère une cellule valide aux coordonnées spécifiées.
     *
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     * @return La cellule aux coordonnées spécifiées si elles sont valides, sinon {@code null}.
     */
    public Cell getValidCell(int row, int col) {
        if (isValidCell(row, col)) {
            return cells.get(row * columns + col);
        }
        return null; // Si la cellule n'est pas valide, retourne null
    }


    /**
     * Place un navire sur la grille et l'affiche graphiquement dans le GridPane.
     *
     * @param ship Le navire à placer.
     * @param gridPane Le conteneur GridPane pour l'affichage graphique.
     */

    public void placeAddShip(Ship ship, GridPane gridPane, Color color){
        Cell startCell = ship.getStart();
        boolean isHorizontal = ship.isHorizontal();
        int startRow = startCell.getRow();
        int startCol = startCell.getCol();

        for (int i = 0; i < ship.getLengthShip(); i++) {
            int currentRow = isHorizontal ? startRow : startRow + i;
            int currentCol = isHorizontal ? startCol + i : startCol;

            Cell cell = cells.get(currentRow * columns + currentCol);
            cell.setOccupied(true);
            cell.setOccupyingShip(ship); // Attribuer le navire à la cellule
        }

        int width = isHorizontal ? ship.getLengthShip() : 1;
        int height = isHorizontal ? 1 : ship.getLengthShip();

        Node shipNode = ship.drawShip(startCell, isHorizontal, color);

        gridPane.add(shipNode, startCell.getCol() + 1, startCell.getRow() + 1, width, height);

        shipNode.toBack();
    }

    /**
     * la methode getOccupiedCells Obtient la liste des cellules occupées par un navire spécifique.
     *
     * @param ship Le navire dont on veut obtenir les cellules occupées.
     * @return La liste des cellules occupées par le navire.
     */

    public List<Cell> getOccupiedCells(Ship ship) {
        List<Cell> occupiedCells = new ArrayList<>();
        int startRow = ship.getStart().getRow();
        int startCol = ship.getStart().getCol();
        
        for (int i = 0; i < ship.getLengthShip(); i++) {
            occupiedCells.add(cells.get(startRow * columns + startCol + i));
            int currentRow = ship.isHorizontal() ? startRow : startRow + i;
            int currentCol = ship.isHorizontal() ? startCol + i : startCol;
            occupiedCells.add(cells.get(currentRow * columns + currentCol));
        } 
        return occupiedCells;
    }

    /**
     * La methode getAllOccupiedCells obtient la liste de toutes les cellules occupées par l'ensemble des navires.
     *
     * @return La liste de toutes les cellules occupées par les navires.
     */
    public List<Cell> getAllOccupiedCells() {
        List<Cell> occupiedCells = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.isOccupied()) {
                occupiedCells.add(cell);
            }
        }
        return occupiedCells;
    }

    /**
     * La methode markOccupiedCellsOnGrid marque les cellules occupées sur un panneau de grille avec la couleur spécifiée.
     *
     * @param gridPane Le panneau de grille où marquer les cellules.
     * @param color La couleur à utiliser pour le marquage.
     */
    
    public void markOccupiedCellsOnGrid(GridPane gridPane, Color color) {
        for (Cell cell : getAllOccupiedCells()) {      
            Label label = getLabelFromCell(cell, gridPane);
            if (label != null) {
                label.setText("O");
                label.setTextFill(color);
                label.setFont(new Font(22));
                label.setAlignment(Pos.CENTER);
            }            
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }
}
