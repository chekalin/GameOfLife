package game;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Lists.newArrayList;

public class Board {

    private final int rows;
    private final int columns;
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
        if (row < 0 || column < 0 || column >= columns || row >= rows) return null;
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
        return newArrayList(Collections2.filter(result, notNull()));
    }

    public void nextGeneration() {
        Board snapshot = takeBoardSnapshot();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                updateCell(row, column, snapshot);
            }
        }
    }

    private Board takeBoardSnapshot() {
        Board result = new Board(getRows(), getColumns());
        for (Cell cell : cells) {
            int currentCellIndex = getCells().indexOf(cell);
            result.getCells().get(currentCellIndex).setAlive(cell.isAlive());
        }
        return result;
    }

    private void updateCell(int row, int column, Board snapshot) {
        Cell cell = getCell(row, column);
        List<Cell> neighbours = snapshot.getNeighbours(row, column);
        Collection<Cell> aliveNeighbours = Collections2.filter(neighbours, aliveCell());
        if (aliveNeighbours.size() < 2 || aliveNeighbours.size() > 3) {
            cell.setAlive(false);
        } else if (aliveNeighbours.size() == 3 && !cell.isAlive()) {
            cell.setAlive(true);
        }
    }

    private Predicate<Cell> aliveCell() {
        return new Predicate<Cell>() {
            public boolean apply(Cell cell) {
                return cell.isAlive();
            }
        };
    }

}
