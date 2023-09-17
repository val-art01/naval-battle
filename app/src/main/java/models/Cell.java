package models;

import javafx.scene.control.Label;

/**
 * La classe Cell représente une cellule dans une grille.
 * Chaque cellule a une position (row, col) et un état de 
 * sélection ou d'occupation par le navire.
 */

public class Cell extends  Label {
    private int row;
    private int col;
    private boolean selected;
    private boolean occupied;
    private boolean isHit;
    private Ship occupyingShip;

    /**
     * Constructeur de la classe Cell.
     *
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     */
    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.selected = false;
        this.occupied = false;
        this.isHit = false;
    }

     /**
     * Retourne la ligne de la cellule.
     *
     * @return La ligne de la cellule.
     */
    public int getRow() {
        return row;
    }

    /**
     * Retourne la colonne de la cellule.
     *
     * @return La colonne de la cellule.
     */
    public int getCol() {
        return col;
    }

    /**
     * Vérifie si la cellule est sélectionnée.
     *
     * @return true si la cellule est sélectionnée, false sinon.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Définit l'état de sélection de la cellule.
     *
     * @param selected true pour sélectionner la cellule, false pour la désélectionner.
     */
    public void setSelected(boolean selected){
        this.selected = selected;
    }

   /**
     * Vérifie si la cellule est occupée par un navire.
     * 
     * @return Vrai si la cellule est occupée, sinon faux.
     */
    public boolean isOccupied() {
        return occupied;
    }
    
    /**
     * Définit l'état d'occupation de la cellule par un navire.
     * 
     * @param occupied Vrai pour indiquer que la cellule est occupée, faux sinon.
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    /**
     * Vérifie si la cellule a été touchée par un tir.
     * 
     * @return Vrai si la cellule a été touchée, sinon faux.
     */
    public boolean isHit() {
        return isHit;
    }
    
    /**
     * Définit l'état d'impact de la cellule suite à un tir.
     * 
     * @param isHit Vrai pour indiquer que la cellule a été touchée, faux sinon.
     */
    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    /**
     * Obtient le navire occupant la cellule, s'il y en a un.
     * 
     * @return Le navire occupant la cellule, ou null si la cellule n'est pas occupée.
     */
    public Ship getOccupyingShip() {
        return occupyingShip;
    }
    
    /**
     * Définit le navire occupant la cellule.
     * 
     * @param occupyingShip Le navire occupant la cellule.
     */
    public void setOccupyingShip(Ship occupyingShip) {
        this.occupyingShip = occupyingShip;
    }    
    
}
