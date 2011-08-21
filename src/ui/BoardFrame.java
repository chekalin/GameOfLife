package ui;


import game.Board;
import game.Cell;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {

    public BoardFrame(Board board) {
        setLayout(new GridLayout(board.getRows(), board.getColumns()));
        for (Cell cell : board.getCells()) {
            add(new CellPanel(cell.isAlive()));
        }
    }
}
