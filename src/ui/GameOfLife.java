package ui;

import game.Board;

import javax.swing.*;

public class GameOfLife {

    public static void main(String[] args) {
        final Board board = new Board(10, 10);
        board.getCell(0, 1).setAlive(true);
        board.getCell(1, 2).setAlive(true);
        board.getCell(2, 0).setAlive(true);
        board.getCell(2, 1).setAlive(true);
        board.getCell(2, 2).setAlive(true);

        board.getCell(0, 8).setAlive(true);
        board.getCell(1, 8).setAlive(true);
        board.getCell(2, 8).setAlive(true);

        board.getCell(8, 0).setAlive(true);
        board.getCell(8, 1).setAlive(true);
        board.getCell(8, 2).setAlive(true);


        BoardFrame boardFrame = new BoardFrame(board);
        boardFrame.setSize(600, 600);
        boardFrame.setTitle("Game of Life");
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setVisible(true);
    }
}
