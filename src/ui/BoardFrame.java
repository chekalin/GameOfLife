package ui;


import game.Board;
import game.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardFrame extends JFrame implements KeyListener {

    private Board board;

    public BoardFrame(Board board) {
        this.board = board;
        this.addKeyListener(this);
        setLayout(new GridLayout(board.getRows(), board.getColumns()));
        for (Cell cell : board.getCells()) {
            add(new CellPanel(cell));
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            board.nextGeneration();
            this.repaint();
        }
    }

    public void keyTyped(KeyEvent e) { }

    public void keyReleased(KeyEvent e) { }
}
