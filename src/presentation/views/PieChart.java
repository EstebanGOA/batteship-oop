package presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Class PieChart that extends JPanel.
 * This class is used to display a pie chart.
 */
public class PieChart extends JPanel {

    private final int totalDegrees = 360;
    private int winrate;
    private int wins;
    private int games;

    /**
     * Constructor of the pie chart with the default values
     */
    public PieChart() {
        this.winrate =  180;
        this.wins = 0;
        this.games = 0;
    }

    private final String WINS = "WINS: ";
    private final String GAMES = "GAMES: ";

    /**
     * This function is used to draw the arcs and fill them.
     * @param g The graphics to paint the Arcs.
     */
    @Override
    public void paint(Graphics g) {
        int startAngle = 90;
        int height = 300;
        int width = 300;
        int y = 0;
        int x = 0;

        int string1_x = 50;
        int string2_x = 200;
        int string_y = 350;

        g.drawString(WINS + wins, string1_x, string_y);
        g.drawString(GAMES + games, string2_x, string_y);


        g.drawArc(x, y, width, height, startAngle, totalDegrees);

        g.setColor(Color.RED);
        g.fillArc(x, y, width, height, startAngle, totalDegrees);


        g.setColor(Color.GREEN);
        g.fillArc(x, y, width, height, startAngle, winrate);




    }

    /**
     * Function that sets the winrate, games won and games played.
     * @param stats An array of integers that has the games won and
     */
    public void setWinrate(int[] stats) {
        double winrate;

        // Check if the player has played at least one game
        if (stats[1] == 0) {
            winrate = 0;
        } else {
            winrate = (double) (stats[0]) / (double) (stats[1]);
        }

        this.winrate = (int) (winrate * totalDegrees);
        this.wins = stats[0];
        this.games = stats[1];
    }
}
