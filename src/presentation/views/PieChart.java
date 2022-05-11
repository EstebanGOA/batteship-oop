package presentation.views;

import javax.swing.*;
import java.awt.*;

public class PieChart extends JPanel {

    public final int x = 0;
    public final int y = 0;
    public final int width = 400;
    public final int height = 400;
    public final int startAngle = 270;
    public final int totalDegrees = 360;

    public int winrate = 0;

    /**
     * Constructor
     * @param winrate is a number between 0 and 1 of the winrate of the player.
     */
    public PieChart(float winrate) {
        this.winrate =  180; //(int) winrate * totalDegrees;
    }

    /**
     * This functions is used to draw the arcs and fill them.
     * @param g The graphis to paint the Arcs.
     */
    @Override
    public void paint(Graphics g) {
        g.drawArc(x, y, width, height, startAngle, totalDegrees);

        g.setColor(Color.RED);
        g.fillArc(x, y, width, height, startAngle, totalDegrees);


        g.setColor(Color.GREEN);
        g.fillArc(x, y, width, height, startAngle, winrate);




    }
}
