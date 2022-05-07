package presentation.views;

import javax.swing.*;
import java.awt.*;

public class GameStageView extends JPanel {

    private final MainView mainView;

    private final Color BACKGROUND_COLOR = new Color(39,152,213);

    public GameStageView (MainView mainView) {
        this.mainView = mainView;

        setBackground();
    }

    public JPanel setBackground () {
        JPanel p = new JPanel();
            p.setBackground(BACKGROUND_COLOR);
            p.setLayout(new BorderLayout());
            p.setPreferredSize(new Dimension(1280, 720));
        return p;
    }
}
