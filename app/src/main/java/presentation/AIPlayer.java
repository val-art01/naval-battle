package presentation;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.Cell;
import models.Grid;
import models.Ship;

public class AIPlayer {

    private Cell selectedBox;
    private Label manhattanDistanceLabel;
    private Label closestShipLengthLabel;

    public AIPlayer(Label manhattanDistanceLabel, Label closestShipLengthLabel) {
        this.manhattanDistanceLabel = manhattanDistanceLabel;
        this.closestShipLengthLabel = closestShipLengthLabel;
    }


    /**
     * Effectue un tir et un déplacement en utilisant des stratégies d'IA.
     * Le joueur effectue des tirs tactiques et groupés pour endommager les bateaux ennemis.
     */
    public void performAIShotAndMove(Grid grid, GridPane gridPane) {
        if (!Ship.allEnemyShipsSunk(grid)) {            
            // aléatoires pour déterminer les positions approximatives des bateaux
            if (!grid.getAllOccupiedCells().isEmpty()) {
                selectedBox = grid.generateRandomStartCell(1, true);
            }    
            // Tir tactique sur les cellules adjacentes
            tacticalShots(grid, gridPane);

        } else {
            // Tous les bateaux sont coulés, la partie est terminée
            // le message de fin de partie avec le score de l'IA
        }
    }

    /**
     * Effectue des tirs tactiques sur les cellules. Cette méthode vise à endommager les bateaux 
     * en ciblant les cellules adjacentes. Si une cellule contenant un bateau  est touchée, 
     * l'IA tentera d'effectuer des tirs groupés pour maximiser ses chances de couler ce bateau.
     */
    private void tacticalShots(Grid grid, GridPane gridPane) {
        if (!selectedBox.isHit()) {
            Shoot shoot = new Shoot(grid, selectedBox);
            List<Cell> adjacentCells = shoot.getAdjacentCells();
            Cell closestAdjacentCell = shoot.getClosestAdjacentCell(adjacentCells);
            if (closestAdjacentCell != null) {
                Shoot tacticalShoot = new Shoot(grid, closestAdjacentCell);
                List<List<Cell>> groupedCells = tacticalShoot.getGroupedCells();
                System.out.println("Case sélectionnée par l'IA : " + (char) ('A' + selectedBox.getRow()) + (selectedBox.getCol() + 1)); 
                updateUIAfterAIShot(tacticalShoot);
                for (List<Cell> group : groupedCells) {
                    Cell targetCell = tacticalShoot.getClosestCell(group);
                    Label cellLabel = grid.getLabelFromCell(targetCell, gridPane);
                    tacticalShoot.markCellLabel(cellLabel, targetCell.isOccupied());

                    if (targetCell != null && !targetCell.isHit()) {
                        tacticalShoot = new Shoot(grid, targetCell);
                        tacticalShoot.markCellLabel(cellLabel, targetCell.isOccupied());
                        break;
                    }
                }
            }
        }
    }
    
     /**
     * updateUIAfterAIShot met à jour l'interface utilisateur après qu'un tir de l'IA a été effectué.
     * Cette méthode met à jour les labels d'affichage pour montrer la distance de Manhattan minimale
     * entre les bateaux ennemis et le bateau le plus proche de l'IA.
     *
     * @param aiShoot L'objet Shoot représentant le tir effectué par l'IA.
     */
    private void updateUIAfterAIShot(Shoot aiShoot) {
        int minDistance = aiShoot.closestShipLength()[0];
        int closestLength = aiShoot.closestShipLength()[1];

        // System.out.println("Tactical Shot - Manhattan Distance: " + minDistance);
        // System.out.println("Tactical Shot - Closest Ship Length: " + closestLength);        

        manhattanDistanceLabel.setText( Integer.toString(minDistance) );
        closestShipLengthLabel.setText(Integer.toString(closestLength));
    }

}
