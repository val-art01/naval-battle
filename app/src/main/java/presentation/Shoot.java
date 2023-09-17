package presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.Cell;
import models.Grid;
import models.Ship;

/**
 * La classe Shoot représente un tir effectué. un tir est associé à une grille de jeu
 * et à une cellule sélectionnée comme cible. La classe offre des méthodes pour calculer 
 * différentes informations concernant le tir, telles que la distance de Manhattan, la 
 * longueur du bateau le plus proche, le nombre de cellules touchées et le nombre de bateaux coulés.
 */

public class Shoot {

    private Grid grid;
    private Cell selectedBox;

    public Shoot(Grid grid, Cell selectedBox){
        this.grid = grid;
        this.selectedBox = selectedBox;
        selectedBox.setHit(true);
    }

    /**
     * Calcule la distance de Manhattan entre deux cellules.
     *
     * @param cell1 La première cellule.
     * @param cell2 La deuxième cellule.
     * @return La distance de Manhattan entre les deux cellules.
     */
    
    private int manhattanDistance(Cell cell1, Cell cell2){
        int rowDistance = Math.abs(cell1.getRow() - cell2.getRow());
        int colDistance = Math.abs(cell1.getCol() - cell2.getCol());       
        return rowDistance + colDistance;
    }

    /**
     * la methode closestShipLength Calcule la longueur du bateau le plus proche de la cellule cible.
     *
     * @return La longueur du bateau le plus proche de la cellule cible.
     */

    public int[] closestShipLength(){
        int minDistance = Integer.MAX_VALUE;
        int closestLength = Integer.MAX_VALUE;
        List<Cell> occupiedCells = grid.getAllOccupiedCells();
        for (Cell cell : occupiedCells) {  
            int distance = manhattanDistance(selectedBox, cell);            
            if (distance < minDistance) {
                minDistance = distance;
                closestLength = cell.getOccupyingShip().getLengthShip();
            } else if (distance == minDistance) {
                closestLength = Math.min(closestLength, cell.getOccupyingShip().getLengthShip());
            }
            
        }
        return new int[] { minDistance, closestLength };
    } 

     /**
     * La methode cellsCount Compte le nombre de cellules touchées par le tir.
     *
     * @return Le nombre de cellules touchées par le tir.
     */
    
    public int cellsCount() {
        int hitCellsCount = 0;
        for (Cell cell : grid.getCells()) {
            if (cell.isOccupied() && cell.isHit()) {
                hitCellsCount++;
            }
        }
        return hitCellsCount;
    }

    /**
     * Compte le nombre de bateaux coulés suite au tir.
     *
     * @return Le nombre de bateaux coulés suite au tir.
     */

    public int sunkShipsCount() {
        // Compter le nombre de bateaux coulés
        int sunkShipsCount = 0;
        List<Ship> sunkShips = new ArrayList<>();

        for (Cell cell : grid.getAllOccupiedCells()) {
            Ship ship = cell.getOccupyingShip();
            if (ship != null && !sunkShips.contains(ship) && ship.isSunk(grid)) {
                sunkShips.add(ship);
                sunkShipsCount++;
            }
        }        
        return sunkShipsCount;
    }  

    /**
     * La methode markCellLabel marque l'étiquette d'une cellule avec la représentation visuelle appropriée en fonction de 
     * son état d'occupation.
     *
     * @param cellLabel L'étiquette associée à la cellule.
     * @param isOccupied La cellule est occupée ou non.
     */
    public void markCellLabel(Label cellLabel, boolean isOccupied) {
        if (isOccupied) {
            cellLabel.setText("O");
            cellLabel.setTextFill(Color.GREEN);
        } else {
            cellLabel.setText("X");
            cellLabel.setTextFill(Color.BLACK);
        }

        cellLabel.setFont(new Font(22));
        cellLabel.setAlignment(Pos.CENTER);
        // cellLabel.toFront();
        cellLabel.setDisable(true); // Désactiver la sélection de cette cellule
    }

    /**
     * getAdjacentCells récupère la liste des cellules adjacentes à la cellule sélectionnée.
     *
     * @return Une liste de cellules adjacentes à la cellule sélectionnée.
     */
    public List<Cell> getAdjacentCells() {
        List<Cell> adjacentCells = new ArrayList<>();
        int row = selectedBox.getRow();
        int col = selectedBox.getCol();

        // Ajoutez les cellules adjacentes (haut, bas, gauche, droite) si elles sont valides
        addAdjacentCell(adjacentCells, row - 1, col);
        addAdjacentCell(adjacentCells, row + 1, col);
        addAdjacentCell(adjacentCells, row, col - 1);
        addAdjacentCell(adjacentCells, row, col + 1);

        return adjacentCells;
    }


    /**
     * addAdjacentCell ajoute une cellule adjacente à la liste si elle est valide.
     *
     * @param adjacentCells La liste où ajouter les cellules adjacentes valides.
     * @param row La ligne de la cellule adjacente.
     * @param col La colonne de la cellule adjacente.
     */
    private void addAdjacentCell(List<Cell> adjacentCells, int row, int col){
        if (grid.isValidCell(row, col)) {
            adjacentCells.add(grid.getValidCell(row, col));
        }
    }


    /**
     * getClosestAdjacentCell récupère la cellule adjacente la plus proche parmi celles fournies.
     *
     * @param adjacentCells La liste de cellules adjacentes à évaluer.
     * @return La cellule adjacente la plus proche, ou {@code null} si la liste est vide.
    */
    public Cell getClosestAdjacentCell(List<Cell> adjacentCells) {
        Cell closestCell = null;
        int minDistance = Integer.MAX_VALUE;

        for (Cell cell : adjacentCells) {
            int distance = manhattanDistance(selectedBox, cell);
            if (distance < minDistance) {
                minDistance = distance;
                closestCell = cell;
            }
        }
        return closestCell;
    }

    /**
     * La méthode getGroupedCells consiste à regrouper les cellules adjacentes en fonction de la distance de Manhattan
     * @return
     */
    public List<List<Cell>> getGroupedCells() {       
        List<Cell> adjacentCells = getAdjacentCells();
        Map<Integer, List<Cell>> groupedCellsMap = new HashMap<>();

        // Regrouper les cellules adjacentes par distance de Manhattan
        for (Cell cell : adjacentCells) {
            int distance = manhattanDistance(selectedBox, cell);
            groupedCellsMap.computeIfAbsent(distance, k -> new ArrayList<>()).add(cell);
        }

        return new ArrayList<>(groupedCellsMap.values());
    }


    /**
     * getClosestCell récupère la cellule la plus proche parmi celles fournies.
     *
     * @param cells La liste de cellules à évaluer.
     * @return La cellule la plus proche, ou {@code null} si la liste est vide.
     */
    public Cell getClosestCell(List<Cell> cells) {
        Cell closestCell = null;
        int minDistance = Integer.MAX_VALUE;

        for (Cell cell : cells) {
            int distance = manhattanDistance(selectedBox, cell);
            if (distance < minDistance) {
                minDistance = distance;
                closestCell = cell;
            }
        }
        return closestCell;
    }
}
