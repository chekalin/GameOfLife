package ui;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel {
    private boolean alive;

    public CellPanel(boolean alive) {
        this.alive = alive;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int height = getSize().height;
        int width = getSize().width;
        if (alive) {
            g.fillRect(0, 0, width, height);
        } else {
            g.drawRect(0, 0, width, height);
        }
    }
}
