package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * Class to instantiate a text inside the enemy JTable.
 *
 */

public class EnemyText extends JPanel {
    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";
    private final JPanel enemyPanel;

    public EnemyText(String shipName) {
        Font font = initializeFont(FONT_BOLD, 12F);

        enemyPanel = new JPanel();
        enemyPanel.setLayout(new BorderLayout());
        enemyPanel.setOpaque(false);

        JLabel shipNameText = new JLabel();
            shipNameText.setText(shipName);
            shipNameText.setForeground(Color.white);
            shipNameText.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            shipNameText.setFont(font);

        enemyPanel.add(shipNameText, BorderLayout.WEST);

        setOpaque(false);
        add(enemyPanel);
    }

    public Font initializeFont(String fontPath, float fontSize) {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font;
    }
}
