package ui;

import game.Cell;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel {
    private Cell cell;

    public CellPanel(Cell cell) {
        this.cell = cell;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int height = getSize().height;
        int width = getSize().width;
        if (cell.isAlive()) {
            g.fillRect(0, 0, width, height);
        } else {
            g.drawRect(0, 0, width, height);
        }
    }
}
