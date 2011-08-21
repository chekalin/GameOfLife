package game;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.IsCollectionContaining.hasItem;

public class BoardTest {

    @Test
    public void shouldGenerateCells() throws Exception {
        Board board = new Board(5, 5);
        assertThat(board.getCells().size(), is(25));

    }

    @Test
    public void shouldReturnFirstCell() throws Exception {
        Board board = new Board(5, 5);
        Cell actual = board.getCell(0, 0);
        Cell expected = board.getCells().get(0);
        assertThat(actual, is(sameInstance(expected)));
    }

    @Test
    public void shouldReturnLastCell() throws Exception {
        Board board = new Board(5, 5);
        Cell actual = board.getCell(4, 4);
        Cell expected = board.getCells().get(board.getCells().size() - 1);
        assertThat(actual, is(sameInstance(expected)));
    }

    @Test
    public void shouldReturnNeighbouringCells() throws Exception {
        Board board = new Board(3, 3);
        List<Cell> neighbours = board.getNeighbours(1, 1);
        assertThat(neighbours.size(), is(8));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(0, 0))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 0))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(2, 0))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(0, 1))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(2, 1))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(0, 2))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 2))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 3))));
    }

    @Test
    public void shouldReturnThreeNeighboursForCornerCell() throws Exception {
        Board board = new Board(3, 3);
        List<Cell> neighbours = board.getNeighbours(0, 0);
        assertThat(neighbours.size(), is(3));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(0, 1))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 0))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 1))));
    }

    @Test
    public void shouldReturnFiveNeighboursForBorderCell() throws Exception {
        Board board = new Board(3, 3);
        List<Cell> neighbours = board.getNeighbours(0, 1);
        assertThat(neighbours.size(), is(5));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(0, 0))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(0, 2))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 0))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 1))));
        assertThat(neighbours, hasItem(sameInstance(board.getCell(1, 2))));
    }

    @Test
    public void cellWithoutNeighboursDies() throws Exception {
        Board board = new Board(3, 3);
        board.getCell(0, 0).setAlive(true);
        board.nextGeneration();
        assertThat(board.getCell(0, 0), isDead());
    }

    @Test
    public void cellWithOneNeighbourDies() throws Exception {
        Board board = new Board(3, 3);
        board.getCell(0, 0).setAlive(true);
        board.getCell(0, 1).setAlive(true);
        board.nextGeneration();
        assertThat(board.getCell(0, 0), isDead());
        assertThat(board.getCell(0, 1), isDead());
    }

    @Test
    public void cellWithTwoNeighboursLives() throws Exception {
        Board board = new Board(3, 3);
        board.getCell(0, 0).setAlive(true);
        board.getCell(0, 1).setAlive(true);
        board.getCell(0, 2).setAlive(true);
        board.nextGeneration();
        assertThat(board.getCell(0, 0), isDead());
        assertThat(board.getCell(0, 1), isAlive());
        assertThat(board.getCell(0, 2), isDead());
    }

    @Test
    public void cellWithThreeNeighboursLives() throws Exception {
        Board board = new Board(3, 3);
        board.getCell(0, 0).setAlive(true);
        board.getCell(1, 1).setAlive(true);
        board.getCell(0, 2).setAlive(true);
        board.getCell(2, 0).setAlive(true);
        board.nextGeneration();
        assertThat(board.getCell(0, 0), isDead());
        assertThat(board.getCell(1, 1), isAlive());
        assertThat(board.getCell(0, 2), isDead());
        assertThat(board.getCell(2, 0), isDead());
    }

    @Test
    public void cellWithMoreThanThreeCellsDies() throws Exception {
        Board board = new Board(3, 3);
        board.getCell(0, 0).setAlive(true);
        board.getCell(1, 1).setAlive(true);
        board.getCell(0, 2).setAlive(true);
        board.getCell(2, 0).setAlive(true);
        board.getCell(2, 2).setAlive(true);
        board.nextGeneration();
        assertThat(board.getCell(0, 0), isDead());
        assertThat(board.getCell(1, 1), isDead());
        assertThat(board.getCell(0, 2), isDead());
        assertThat(board.getCell(2, 0), isDead());
        assertThat(board.getCell(2, 2), isDead());
    }

    @Test
    public void deadCellWithThreeNeighboursBecomesAlive() throws Exception {
        Board board = new Board(3, 3);
        board.getCell(0, 1).setAlive(true);
        board.getCell(1, 0).setAlive(true);
        board.getCell(1, 1).setAlive(true);
        board.nextGeneration();
        assertThat(board.getCell(0, 0), isAlive());
    }

    @Test
    public void deadCellWithThreeNeighboursBecomesAlive2() throws Exception {
        Board board = new Board(10, 10);
        board.getCell(1, 1).setAlive(true);
        board.getCell(1, 2).setAlive(true);
        board.getCell(1, 3).setAlive(true);
        board.nextGeneration();
        assertThat(board.getCell(0, 2), isAlive());
        assertThat(board.getCell(2, 2), isAlive());
    }

    private Matcher<Cell> isDead() {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object o) {
                Cell cell = (Cell) o;
                return !cell.isAlive();
            }

            public void describeTo(Description description) {
                description.appendText("cell was not dead, but alive");
            }
        };
    }

    private Matcher<Cell> isAlive() {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object o) {
                Cell cell = (Cell) o;
                return cell.isAlive();
            }

            public void describeTo(Description description) {
                description.appendText("cell was not alive, but dead");
            }
        };
    }
}
