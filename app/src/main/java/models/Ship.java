package models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * 
 *  La classe Ship représente un navire individuel qui prend la longueur et l'orientation du navire. 
 *  La méthode drawShip prend en compte une cellule de départ et l'orientation du navire lors du dessin. 
 *  La méthode isHidden a été retirée car elle n'était pas utilisée dans cette classe.
 * 
 */

public class Ship {

    private Cell start;
    private boolean isHorizontal; 
    private int lengthShip;
    private final int CELLSIZE = 35;

    public Ship(int lengthShip, boolean isHorizontal) {
        this.lengthShip = lengthShip;
        this.isHorizontal = isHorizontal;            
    }

    /**
     * La methode drawShip dessine un navire représenté par un rectangle à 
     * partir de la cellule de départ spécifiée. La direction du navire est 
     * déterminée par le paramètre isHorizontal.
     *
     * @param start La cellule de départ pour dessiner le navire.
     * @param isHorizontal Détermine si le navire doit être dessiné horizontalement (true) ou verticalement (false).
     * @return Un objet de type Shape (un rectangle) représentant le navire dessiné.
     */

    public Shape drawShip(Cell start, boolean isHorizontal, Color color){  
        int startX = start.getRow() * CELLSIZE; 
        int startY = start.getCol()* CELLSIZE; 
        Rectangle rect;

        if (isHorizontal) {
            int width = lengthShip * CELLSIZE ; // Largeur du rectangle du navire en pixels
            rect = new Rectangle(startX, startY, width, CELLSIZE);
        } else {
            int height = lengthShip * CELLSIZE ; // Largeur du rectangle du navire en pixels
            rect = new Rectangle(startX, startY, CELLSIZE, height);       
        }
        rect.setFill(color);
        return rect;
    }

    /**
     * La methode isSunk vérifie si le navire est coulé en fonction de son état actuel sur la grille.
     *
     * @param grid La grille sur laquelle le navire est placé.
     * @return {@code true} si toutes les cellules du navire ont été touchées, indiquant que le navire est coulé,
     *         {@code false} sinon.
     */
    public boolean isSunk(Grid grid) {
        for (int i = 0; i < lengthShip; i++) {
            int row;
            int col;
            
            if (isHorizontal) {
                row = start.getRow();
                col = start.getCol() + i;
            } else {
                row = start.getRow() + i;
                col = start.getCol();
            }

            Cell cell = grid.getCells().get(row * grid.getColumns() + col);
            if (!cell.isHit()) {
                return false; // Si une cellule n'est pas touchée, le navire n'est pas coulé
            }
        }
        return true; // Toutes les cellules sont touchées, le navire est coulé
    }

    /**
     * La methode allEnemyShipsSunk vérifie si toutes les navires sont coulés sur la grille donnée.
     *
     * @param grid La grille à vérifier.
     * @return {@code true} si tous les navires sont coulés, sinon {@code false}.
     */
    public static boolean allEnemyShipsSunk(Grid grid) {
        for (Cell cell : grid.getCells()) {
            if (cell.isOccupied() && !cell.getOccupyingShip().isSunk(grid)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Récupère la cellule de départ.
     *
     * @return La cellule de départ.
     */
    public Cell getStart() {
        return start;
    }

    /**
     * Définit la cellule de départ.
     *
     * @param start La nouvelle cellule de départ à définir.
     */
    public void setStart(Cell start) {
        this.start = start;
    }

    /**
     * Vérifie si le navire est disposé horizontalement.
     *
     * @return {@code true} si le navire est disposé horizontalement, sinon {@code false}.
     */
    public boolean isHorizontal() {
        return isHorizontal;
    }

    /**
     * Définit l'orientation du navire.
     *
     * @param isHorizontal {@code true} si le navire doit être orienté horizontalement, sinon {@code false}.
     */
    public void setHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    /**
     * Récupère la longueur du navire.
     *
     * @return La longueur du navire.
     */
    public int getLengthShip() {
        return lengthShip;
    }

}
