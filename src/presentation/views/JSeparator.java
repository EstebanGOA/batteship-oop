package presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Class JSeparator that extends a JPanel.
 * Is used to create separations.
 */
public class JSeparator extends JPanel {

    private final JPanel space;

    /**
     *
     * Method to create a JSeparator class.
     * It separates components between them, giving it a width and a height.
     *
     * @param width distance to separate the components.
     * @param height distance to separate the components.
     *
     */

    public JSeparator (int width, int height) {
        space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));

        Component rigidArea = Box.createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        setOpaque(false);
        add(space);
    }
}
