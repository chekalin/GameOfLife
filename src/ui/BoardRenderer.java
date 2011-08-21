package ui;

import game.Board;

import javax.swing.*;

public class BoardRenderer {

    public static void main(String[] args) {
        final Board board = new Board(50, 50);
        board.getCell(1, 1).setAlive(true);
        board.getCell(1, 2).setAlive(true);
        board.getCell(1, 3).setAlive(true);
        BoardFrame boardFrame = new BoardFrame(board);
        boardFrame.setSize(600, 600);
        boardFrame.setTitle("Game of Life");
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setVisible(true);
    }
}
