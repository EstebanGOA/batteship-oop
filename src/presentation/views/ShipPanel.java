package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class ShipPanel that extends JPanel.
 */
public class ShipPanel extends JPanel {

    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";
    private final JImagePanel shipPanel;

    /**
     *
     * Constructor to draw a ShipPanel for the game stage view
     *
     * @param shipName name of the ship
     * @param shipPath image of the ship
     * @param panelBg background of the ship
     * @param imageWidth image width
     * @param imageHeight image height
     * @param widthShipPanel width of the panel
     *
     */

    public ShipPanel(String shipName, SpritePath shipPath, SpritePath panelBg, int imageWidth, int imageHeight, int widthShipPanel) {
        Font font = initializeFont(FONT_BOLD, 15F);

        shipPanel = new JImagePanel(panelBg);
        shipPanel.setPreferredSize(new Dimension(widthShipPanel, 125));
        shipPanel.setLayout(new GridBagLayout());
        shipPanel.setOpaque(false);

        JLabel shipNameText = new JLabel();
        shipNameText.setText(shipName);
        shipNameText.setForeground(Color.white);
        shipNameText.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        shipNameText.setFont(font);

        JImagePanel yourShipImage = new JImagePanel(shipPath);
        yourShipImage.setPreferredSize(new Dimension(imageWidth, imageHeight));
        yourShipImage.setOpaque(false);

        GridBagConstraints gbc_yourShips = new GridBagConstraints();
        gbc_yourShips.gridx = 0;
        gbc_yourShips.gridy = 0;
        shipPanel.add(shipNameText, gbc_yourShips);

        gbc_yourShips.gridx = 0;
        gbc_yourShips.gridy = 1;
        shipPanel.add(yourShipImage, gbc_yourShips);

        setBorder(BorderFactory.createEmptyBorder(-15, 0, -15, 0));
        setOpaque(false);
        add(shipPanel);
    }

    /**
     *
     * Method to initialize the font.
     *
     * @param fontPath font path.
     * @param fontSize font size.
     *
     * @return the font initialized.
     *
     */

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

    /**
     *
     * Method to get the background panel of the ship panel.
     *
     * @return the background panel.
     *
     */

    public JImagePanel getBackgroundPanel () {
        return shipPanel;
    }
}
