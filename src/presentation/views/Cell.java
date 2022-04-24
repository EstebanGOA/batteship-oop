package presentation.views;

import javax.swing.*;

public class Cell extends JPanel {
    private int x;
    private int y;

    private String img;

    public Cell (int x, int y, String img) {
        this.x = x;
        this.y = y;
        this.img = img;

        add(new JImagePanel(img));
    }
}
