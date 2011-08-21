package game;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Board {

    private int rows;
    private int columns;
    private List<Cell> cells;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cells = newArrayList();
        for (int i = 0; i < rows * columns; i++) {
            cells.add(new Cell());
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

    public Cell getCell(int row, int column) {
        if (row < 0 || column < 0 || column > columns || row > rows) return null;
        int index = row * columns + column;
        return cells.get(index);
    }

    public List<Cell> getNeighbours(int row, int column) {
        List<Cell> result = newArrayList();
        int previousRow = row - 1;
        int nextRow = row + 1;
        int previousColumn = column - 1;
        int nextColumn = column + 1;
        result.add(getCell(previousRow, previousColumn));
        result.add(getCell(previousRow, column));
        result.add(getCell(previousRow, nextColumn));
        result.add(getCell(row, previousColumn));
        result.add(getCell(row, nextColumn));
        result.add(getCell(nextRow, previousColumn));
        result.add(getCell(nextRow, column));
        result.add(getCell(nextRow, nextColumn));
        return result;
    }

}
