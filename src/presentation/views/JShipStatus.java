package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JShipStatus extends JPanel {

    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";
    private final JImagePanel shipStatusPanel;

    private JLabel status;

    public JShipStatus (String shipStatus, int widthShipStatusPanel) {
        Font font = initializeFont(FONT_BOLD, 15F);

        shipStatusPanel = new JImagePanel(SpritePath.SHIP_PANEL_BACKGROUND);
        shipStatusPanel.setPreferredSize(new Dimension(widthShipStatusPanel, 125));
        shipStatusPanel.setLayout(new BorderLayout());
        shipStatusPanel.setOpaque(false);

        status = new JLabel();
        status.setText(shipStatus);
        status.setForeground(Color.white);
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setVerticalAlignment(SwingConstants.CENTER);
        status.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        status.setFont(font);

        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(-15, 0, -15, 0));
        shipStatusPanel.add(status, BorderLayout.CENTER);
        add(shipStatusPanel);
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
    }
}
