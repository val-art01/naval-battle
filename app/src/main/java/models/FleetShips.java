package models;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *  
 * la classe FleetShips gére le nombre de navires et leur longueur
 * 
 */

public class FleetShips {

    private int numberShip;
    private List<Integer> shipLengths;
    List<Ship> ships;
    
    public FleetShips (int numberShip){
        this.numberShip = numberShip;
        this.shipLengths = new ArrayList<>();
        this.ships = new ArrayList<>();
    }

    /**
     * 
     * la methode setShipLengths Définit les longueurs des navires et initialise les navires avec des orientations aléatoires.
     *
     * @param shipLengths Une liste d'entiers représentant les longueurs des vaisseaux à définir.
     * 
     * @throws IllegalArgumentException Si une longueur de navire n'est pas comprise dans l'intervalle valide [1, 6],
     * ou si le nombre de vaisseaux créés ne correspond pas à la taille de la liste fournie.
     */

    public void setShipLengths(List<Integer> shipLengths) {
        for (int length : shipLengths) {
            if (length < 1 || length > 6) {
                throw new IllegalArgumentException("La longueur de chaque navire doit être comprise entre 1 et 6.");
            }else{
                boolean isHorizontal = Math.random() < 0.5;
                Ship ship = new Ship(length, isHorizontal);
                this.ships.add(ship);
            }
        }

        if (numberShip == shipLengths.size()) {
             this.shipLengths = shipLengths;
        } else {
            throw new IllegalArgumentException("Le nom de longueur de navire est insufisante.");
        }       
    } 

    /**
     * ShipPlacer place la flotte de navires aléatoirement dans la grille de jeu.
     *
     * @param grid La grille de jeu dans laquelle placer les navires.
     */

    public void ShipPlacer (Grid grid, GridPane gridPane, Color color) {
        for (Ship ship : ships) {
           boolean placed = false;
           while (!placed) {
               Cell startCell = grid.generateRandomStartCell(ship.getLengthShip(), ship.isHorizontal());
               ship.setStart(startCell);
               if (grid.canPlaceShip(ship)) {
                   grid.placeAddShip(ship, gridPane, color);
                   placed = true;
               }
           }
        }                
    }


    public int getNumberShip() {
        return numberShip;
    }

    public List<Integer> getShipLengths() {
        return shipLengths;
    }

    public List<Ship> getShips() {
        return ships;
    }

}
