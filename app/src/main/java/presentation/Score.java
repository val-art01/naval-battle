package presentation;

import models.Cell;
import models.Grid;

/**
 * la classe Score représente le calcul du score d'une grille de jeu de bataille navale.
 */

public class Score {

    private Grid grid;

    public Score(Grid grid) {
        this.grid = grid;
    }

     /**
     * la methode calculateScore Calcule le score en fonction de la grille de jeu.
     *
     * @return Le score calculé.
     */
    public double calculateScore() {
        int totalCells = grid.getRows() * grid.getColumns();
        int totalTouchedCells = getTotalTouchedCells();
        int totalEnemyShipsSunk = getTotalEnemyShipsSunk();

        double score = calculateScore(totalCells, totalTouchedCells, totalEnemyShipsSunk);
        return score;
    }

    /**
     * la methode getTotalTouchedCells Compte le nombre total de cellules touchées dans la grille.
     *
     * @return Le nombre total de cellules touchées.
     */
    private int getTotalTouchedCells() {
        int touchedCellsCount = 0;

        for (Cell cell : grid.getCells()) {
            if (cell.isHit()) {
                touchedCellsCount++;
            }
        }

        return touchedCellsCount;
    }

      /**
     * la methode getTotalEnemyShipsSunk Compte le nombre total de navires ennemis coulés dans la grille.
     *
     * @return Le nombre total de navires ennemis coulés.
     */
    private int getTotalEnemyShipsSunk() {
        int sunkShipsCount = 0;
        for (Cell cell : grid.getCells()) {
            if (cell.isOccupied() && cell.isHit() && cell.getOccupyingShip().isSunk(grid)) {
                sunkShipsCount++;
            }
        }

        return sunkShipsCount;
    }

     /**
     * la methode calculateScore Calcule le score en fonction du nombre total de cellules, du nombre 
     * de cellules touchées et du nombre de navires ennemis coulés.
     *
     * @param totalCells Le nombre total de cellules dans la grille.
     * @param touchedCells Le nombre de cellules touchées.
     * @param enemyShipsSunk Le nombre de navires ennemis coulés.
     * @return Le score calculé.
     */
    private static double calculateScore(int totalCells, int touchedCells, int enemyShipsSunk) {
        double percentageTouched = (double) touchedCells / totalCells;
        double score = percentageTouched * enemyShipsSunk;

        return score;
    }

    /**
     * la methode isMoreEffective Compare deux scores pour déterminer lequel est plus efficace.
     *
     * @param score1 Le premier score à comparer.
     * @param score2 Le deuxième score à comparer.
     * @return True si le premier score est plus efficace, sinon False.
     */
    public static boolean isMoreEffective(double score1, double score2) {
        return score1 > score2;
    }
}

