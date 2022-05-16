package presentation.views;

import javax.swing.*;
import java.awt.*;

public class JSeparator extends JPanel {

    private final JPanel space;

    public JSeparator (int width, int height) {
        space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));

        Component rigidArea = Box.createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        setOpaque(false);
        add(space);
    }
}
