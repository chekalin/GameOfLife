package com.example.game;


import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {
    private Board board;

    public BoardFrame(Board board) {
        this.board = board;
        setLayout(new GridLayout(board.getRows(), board.getColumns()));
        for (Cell cell : board.getCells()) {
            add(new CellPanel(cell.isAlive()));
        }
    }
}
