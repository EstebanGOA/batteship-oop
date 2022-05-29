package presentation.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * BarChart class extends a JPanel, and it's used to create the barchart in the Statistics View
 */
public class BarChart extends JPanel {

    private ArrayList<Integer> bars = new ArrayList<>();

    /**
     * Function that adds a bar in the barchart .
     * @param value the value of the bar.
     */
    public void addBar( int value) {
        bars.add( value);
        repaint();
    }

    /**
     * Function that paints the bar chart
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        int max = Integer.MIN_VALUE;

        for (Integer value : bars) {

            max = Math.max(max, value);
        }

        int width = 0;
        if (bars.size() > 0) {
            width = (getWidth() / bars.size()) - 2;
        }

        int x = 1;
        int bar = 0;

        for (int value : bars) {

            int height = (int) ((getHeight() - 40) * ((double) value / max));

            g.setColor(new Color((int) (Math.random() * 0x1000000)));

            g.fillRect(x, getHeight() - height -20 , width, height);

            g.setColor(Color.black);

            g.drawRect(x, getHeight() - height - 20, width, height);



            g.drawString(value +" Attacks", x + width /4,  getHeight() - height - 30);

            g.drawString("Game " + (bar + 1), x + width /4,  getHeight() - 5);

            x += (width + 2);
            bar++;

        }

    }

    /**
     * Function that removes all the bars.
     */
    public void clearBars() {
        bars.clear();
    }

}

