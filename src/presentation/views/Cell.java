package presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Class Cell extends JPanel.
 */
public class Cell extends JPanel {

    private final int x;
    private final int y;
    private JLabel text;

    /**
     *
     * Constructor of the cell class.
     *
     * @param x x coordinate of the cell.
     * @param y y coordinate of the cell.
     *
     */

    public Cell (int x, int y) {
        super();
        text = new JLabel();
        add(text, BorderLayout.CENTER);
        this.x = x;
        this.y = y;
    }

    /**
     * Function that updates the text.
     * @param text A string with the new text.
     */
    public void updateText(String text) {
        this.text.setText(text);
    }

    /**
     *
     * Method to return the coordinates of the cell.
     *
     * @return the coordinates of the cell.
     *
     */

    public int[] getCoordinates() {
        return new int[]{x, y};
    }
}
