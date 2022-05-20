package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class to instantiate a text inside the enemy JTable.
 */

public class EnemyText extends JPanel {

    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";
    private final JPanel enemyPanel;

    private JLabel status;

    public EnemyText(String shipName) {

        Font font = initializeFont(FONT_BOLD, 12F);
        // Necesitamos dar un valor por defecto que sea igual de largo que el texto más largo que vamos a introducir si no
        // no se actualizará.
        status = new JLabel("Default text for ship status");

        enemyPanel = new JPanel();
        enemyPanel.setLayout(new BorderLayout());
        enemyPanel.setOpaque(false);

        status.setText(shipName);
        status.setForeground(Color.white);
        status.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        status.setFont(font);

        enemyPanel.add(status, BorderLayout.WEST);

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

    public void updateStatus(boolean sunk) {
        if (sunk) {
            status.setText("Sunk");
            status.setForeground(Color.RED);
        } else {
            status.setText("Alive");
            status.setForeground(Color.WHITE);
        }
        status.paintImmediately(status.getVisibleRect());
    }
}
