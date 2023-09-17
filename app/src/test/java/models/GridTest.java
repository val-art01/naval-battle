// package models;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// class GridTest {
//     int rows = 3;
//     int columns = 3;

//     @Test
//     public void createGridWithGraphics() {
        // Grid grid = new Grid(rows, columns);
//        GridPane gridPane = new GridPane();
//        grid.createGridWithGraphics(gridPane);
//
//        ObservableList<Node> children = gridPane.getChildren();
//        assertEquals(rows * columns, children.size());
        // assertEquals(rows * columns, 9);

//        int cellIndex = 0;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                Node node = children.get(cellIndex);
//                assertTrue(node instanceof Label);
//
//                Label label = (Label) node;
//                Cell cell = grid.getCells().get(cellIndex);
//
//                assertEquals(label, grid.getLabelFromCell(cell, gridPane));
//
//                cellIndex++;
//            }
//        }
    // }

//    @Test
//    public void testGetLabelFromCell() {
//        Grid grid = new Grid(rows, columns);
//        GridPane gridPane = new GridPane();
//        grid.createGridWithGraphics(gridPane);
//
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                Cell cell = grid.getCells().get(i * columns + j);
//                Label expectedLabel = (Label) gridPane.getChildren().get(i * columns + j);
//                Label actualLabel = grid.getLabelFromCell(cell, gridPane);
//                assertEquals(expectedLabel, actualLabel);
//            }
//        }
//    }

// }