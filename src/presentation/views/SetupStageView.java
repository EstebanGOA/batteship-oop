package presentation.views;

import business.entities.Submarine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SetupStageView extends JPanel implements MouseListener {

    private final MainView mainView;

    private ShipPanel boatPanel;
    private ShipPanel submarinePanel1;
    private ShipPanel submarinePanel2;
    private ShipPanel destructorPanel;
    private ShipPanel aircraftPanel;

    private JImagePanel shipImage;

    // Image Path
    private final String SPRITE_WATER = "sprites/GameViews/water.png";
    private final String SPRITE_YOUR_SHIPS_BG = "sprites/GameViews/your_ships_panel.png";
    private final String SPRITE_ROTATION_SHIP = "sprites/GameViews/boat.png";
    private final String SPRITE_BOAT = "sprites/GameViews/SetupStageView/rotated_boat.png";
    private final String SPRITE_SUBMARINE = "sprites/GameViews/SetupStageView/rotated_submarine.png";
    private final String SPRITE_AIRCRAFT = "sprites/GameViews/SetupStageView/rotated_aircraft.png";
    private final String SPRITE_DESTRUCTOR = "sprites/GameViews/SetupStageView/rotated_destructor.png";
    private final String SPRITE_BG2 = "sprites/GameViews/SetupStageView/bg2_panel.png";
    private final String SPRITE_ROTATE_BTN_BG = "sprites/GameViews/SetupStageView/bg_rotate_btn.png";
    private final String SPRITE_ROTATE_ARROW_ICO = "sprites/GameViews/SetupStageView/rotated_arrow.png";
    private final String SPRITE_FULL_USER = "sprites/GameViews/SetupStageView/full_user.png";
    private final String SPRITE_EMPTY_USER = "sprites/GameViews/SetupStageView/empty_user.png";
    private final String SPRITE_ATTACK_BTN_BG = "sprites/GameViews/SetupStageView/start_attack_bg.png";
    private final String SPRITE_ATTACK_ICO = "sprites/GameViews/SetupStageView/attack_icon.png";

    // Strings

    private final String LABEL_SHIP_PREVIEW = "Ship Preview";
    private final String LABEL_ROTATE_BTN = "ROTATE";
    private final String LABEL_NUMBER_ENEMIES = "Number of Enemies";
    private final String LABEL_ATTACK_BTN = "START ATTACK";

    // Background Color

    private final Color BACKGROUND_COLOR = new Color(39, 152, 213);

    // Font Path

    private final String FONT_BLACK = "fonts/Poppins-Black.ttf";
    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";

    private final Font fontStartAttack = initializeFont(FONT_BLACK, 20F);
    private final Font fontPanelTitle = initializeFont(FONT_BOLD, 18F);

    private Cell[][] table = new Cell[15][15];

    public SetupStageView(MainView mainView) {

        this.mainView = mainView;

        // ------------------------ Background Image ------------------------ //

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setPreferredSize(new Dimension(1280, 720));
        backgroundPanel.setBackground(BACKGROUND_COLOR);
        backgroundPanel.setLayout(new GridBagLayout());

        // Display all the panels in the background.

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(leftPanel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        backgroundPanel.add(addSeparator(10, 0));

        gbc.gridx = 2;
        gbc.gridy = 0;
        backgroundPanel.add(table(), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        backgroundPanel.add(addSeparator(10, 0));

        gbc.gridx = 4;
        gbc.gridy = 0;
        backgroundPanel.add(rightPanel(), gbc);

        add(backgroundPanel);

        initializeListeners();
    }

    private void initializeListeners() {
        boatPanel.addMouseListener(this);
        submarinePanel1.addMouseListener(this);
        submarinePanel2.addMouseListener(this);
        destructorPanel.addMouseListener(this);
        aircraftPanel.addMouseListener(this);
    }

    /**
     * Method to create the left panel that has:
     * <p>
     * 1. The title of the panel.
     * 2. We instantiate a custom class (ShipPanel) that has:
     * 2.1 The name of the ship
     * 2.2 The path of the image of the ship.
     *
     * @return The JPanel with all inside the left panel.
     */
    public JPanel leftPanel() {

        // --------------------------- Left Panel -------------------------- //

        JImagePanel leftPanel = new JImagePanel(SPRITE_YOUR_SHIPS_BG);
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(300, 670));
        leftPanel.setOpaque(false);

        // Label with the title of the panel

        JLabel yourShipsText = new JLabel();
        yourShipsText.setText("Your Ships");
        yourShipsText.setForeground(Color.white);
        yourShipsText.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        yourShipsText.setFont(fontPanelTitle);

        // All the ships buttons to locate the ships in the table

        boatPanel = new ShipPanel("Boat", SPRITE_BOAT, 60, 25);
        boatPanel.setName("boat");
        submarinePanel1 = new ShipPanel("Submarine 1", SPRITE_SUBMARINE, 80, 25);
        submarinePanel1.setName("submarine");
        submarinePanel2 = new ShipPanel("Submarine 2", SPRITE_SUBMARINE, 80, 25);
        submarinePanel2.setName("submarine");
        destructorPanel = new ShipPanel("Destructor", SPRITE_DESTRUCTOR, 100, 25);
        destructorPanel.setName("destructor");
        aircraftPanel = new ShipPanel("Aircraft", SPRITE_AIRCRAFT, 120, 25);
        aircraftPanel.setName("aircraft");

        // Display all the things inside the left panel.

        GridBagConstraints gbc_left = new GridBagConstraints();
        gbc_left.gridx = 0;
        gbc_left.gridy = 0;
        leftPanel.add(yourShipsText, gbc_left);

        gbc_left.gridx = 0;
        gbc_left.gridy = 1;
        leftPanel.add(boatPanel, gbc_left);

        gbc_left.gridx = 0;
        gbc_left.gridy = 2;
        leftPanel.add(submarinePanel1, gbc_left);

        gbc_left.gridx = 0;
        gbc_left.gridy = 3;
        leftPanel.add(submarinePanel2, gbc_left);

        gbc_left.gridx = 0;
        gbc_left.gridy = 4;
        leftPanel.add(destructorPanel, gbc_left);

        gbc_left.gridx = 0;
        gbc_left.gridy = 5;
        leftPanel.add(aircraftPanel, gbc_left);

        gbc_left.gridx = 0;
        gbc_left.gridy = 6;
        leftPanel.add(addSeparator(0, 15), gbc_left);

        return leftPanel;
    }

    /**
     * Method to create the grid table (15x15) to locate the ships.
     * <p>
     * Is a loop for where we instantiate the cell class.
     * The Cell class has the x and y position and the path of the image we want to load.
     *
     * @return The JPanel with all the grid created.
     */

    public JPanel table() {
        JPanel tableGrid = new JPanel();
        tableGrid.setLayout(new GridLayout(15, 15));
        tableGrid.setPreferredSize(new Dimension(650, 650));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                table[i][j] = new Cell(j, i, SPRITE_WATER);
                tableGrid.add(table[i][j]);
            }
        }

        return tableGrid;
    }

    /**
     * Method to create the preview panel with:
     * <p>
     * 1. The title of the panel (text).
     * 2. The preview rotation of the ship (image).
     * 3. The button to rotate the ship (text + icon).
     *
     * @return the JPanel with the preview panel.
     */

    public JPanel previewPanel() {

        // --------------------------- Preview Panel --------------------------- //

        JImagePanel shipPreviewPanel = new JImagePanel(SPRITE_BG2);
        shipPreviewPanel.setPreferredSize(new Dimension(270, 280));
        shipPreviewPanel.setLayout(new GridBagLayout());
        shipPreviewPanel.setOpaque(false);

        // Label with the title of the panel.

        JLabel shipPreviewText = new JLabel();
        shipPreviewText.setText(LABEL_SHIP_PREVIEW);
        shipPreviewText.setForeground(Color.white);
        shipPreviewText.setBorder(BorderFactory.createEmptyBorder(10, 35, 35, 0));
        shipPreviewText.setFont(fontPanelTitle);

        // Image of the ship that is selected.

        shipImage = new JImagePanel(SPRITE_SUBMARINE);
        shipImage.setPreferredSize(new Dimension(40, 80));
        shipImage.setOpaque(false);

        // Button to rotate the selected ship.

        JImagePanel rotateButton = new JImagePanel(SPRITE_ROTATE_BTN_BG);
        rotateButton.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        rotateButton.setPreferredSize(new Dimension(200, 45));
        rotateButton.setOpaque(false);

        // Label with the text of the button rotate.

        JLabel rotateButtonText = new JLabel();
        rotateButtonText.setText(LABEL_ROTATE_BTN);
        rotateButtonText.setForeground(Color.white);
        rotateButtonText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        rotateButtonText.setFont(fontPanelTitle);

        // Icon of the rotation button.

        JImagePanel rotateIcon = new JImagePanel(SPRITE_ROTATE_ARROW_ICO);
        rotateIcon.setPreferredSize(new Dimension(25, 25));
        rotateIcon.setOpaque(false);

        // Display the text and the icon of the rotation button.

        GridBagConstraints gbc_rotateBtn = new GridBagConstraints();
        gbc_rotateBtn.fill = GridBagConstraints.HORIZONTAL;

        gbc_rotateBtn.gridx = 0;
        gbc_rotateBtn.gridy = 0;
        gbc_rotateBtn.gridwidth = 3;
        rotateButton.add(rotateButtonText, gbc_rotateBtn);

        gbc_rotateBtn.gridx = 3;
        gbc_rotateBtn.gridy = 0;
        gbc_rotateBtn.gridwidth = 1;
        rotateButton.add(rotateIcon, gbc_rotateBtn);

        // Display all the preview panel.

        GridBagConstraints gbc_shipPreview = new GridBagConstraints();
        gbc_shipPreview.fill = GridBagConstraints.HORIZONTAL;

        gbc_shipPreview.gridx = 0;
        gbc_shipPreview.gridy = 0;
        gbc_shipPreview.gridwidth = 3;
        shipPreviewPanel.add(shipPreviewText, gbc_shipPreview);

        gbc_shipPreview.gridx = 0;
        gbc_shipPreview.gridy = 1;
        gbc_shipPreview.gridwidth = 1;
        shipPreviewPanel.add(addSeparator(75, 0), gbc_shipPreview);

        gbc_shipPreview.gridx = 1;
        gbc_shipPreview.gridy = 1;
        gbc_shipPreview.gridwidth = 1;
        shipPreviewPanel.add(shipImage, gbc_shipPreview);

        gbc_shipPreview.gridx = 0;
        gbc_shipPreview.gridy = 2;
        gbc_shipPreview.gridwidth = 3;
        shipPreviewPanel.add(addSeparator(0, 20), gbc_shipPreview);

        gbc_shipPreview.gridx = 0;
        gbc_shipPreview.gridy = 3;
        gbc_shipPreview.gridwidth = 3;
        shipPreviewPanel.add(rotateButton, gbc_shipPreview);

        return shipPreviewPanel;
    }

    /**
     * Method to create the number of enemies panel that has:
     * <p>
     * 1. The title of the panel.
     * 2. 4 enemies icons, to select the number of enemies.
     *
     * @return the JPanel with all inside the numberOfEnemiesPanel.
     */

    public JPanel numberOfEnemiesPanel() {

        // --------------------------- Number Of Enemies Panel --------------------------- //

        JImagePanel numberOfEnemiesPanel = new JImagePanel(SPRITE_BG2);
        numberOfEnemiesPanel.setPreferredSize(new Dimension(270, 280));
        numberOfEnemiesPanel.setLayout(new GridBagLayout());
        numberOfEnemiesPanel.setOpaque(false);

        // Text with the title of the panel.

        JLabel numberOfEnemiesText = new JLabel();
        numberOfEnemiesText.setText(LABEL_NUMBER_ENEMIES);
        numberOfEnemiesText.setForeground(Color.white);
        numberOfEnemiesText.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));
        numberOfEnemiesText.setFont(fontPanelTitle);

        // GridBagLayout with all the enemies icons.

        JPanel numberOfEnemiesGrid = new JPanel();
        numberOfEnemiesGrid.setLayout(new GridBagLayout());
        numberOfEnemiesGrid.setOpaque(false);

        // Enemy icon number 1. (Is always selected because the minimum number of enemies is 1).

        JImagePanel enemy1 = new JImagePanel(SPRITE_FULL_USER);
        enemy1.setPreferredSize(new Dimension(30, 30));
        enemy1.setOpaque(false);

        // Enemy icon number 2. (Is empty, can be selected).

        JImagePanel enemy2 = new JImagePanel(SPRITE_EMPTY_USER);
        enemy2.setPreferredSize(new Dimension(30, 30));
        enemy2.setOpaque(false);

        // Enemy icon number 3. (Is empty, can be selected).

        JImagePanel enemy3 = new JImagePanel(SPRITE_EMPTY_USER);
        enemy3.setPreferredSize(new Dimension(30, 30));
        enemy3.setOpaque(false);

        // Enemy icon number 4. (Is empty, can be selected).

        JImagePanel enemy4 = new JImagePanel(SPRITE_EMPTY_USER);
        enemy4.setPreferredSize(new Dimension(30, 30));
        enemy4.setOpaque(false);

        // Display all the enemies icons with separators panels between them.

        GridBagConstraints gbc_numberOfEnemiesGrid = new GridBagConstraints();
        gbc_numberOfEnemiesGrid.gridx = 0;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(enemy1, gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 1;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(addSeparator(10, 0), gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 2;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(enemy2, gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 3;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(addSeparator(10, 0), gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 4;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(enemy3, gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 5;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(addSeparator(10, 0), gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 6;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(enemy4, gbc_numberOfEnemiesGrid);

        // Display all the things inside the number of enemies panel.

        GridBagConstraints gbc_numberOfEnemiesPanel = new GridBagConstraints();
        gbc_numberOfEnemiesPanel.gridx = 0;
        gbc_numberOfEnemiesPanel.gridy = 0;
        numberOfEnemiesPanel.add(numberOfEnemiesText, gbc_numberOfEnemiesPanel);

        gbc_numberOfEnemiesPanel.gridx = 0;
        gbc_numberOfEnemiesPanel.gridy = 1;
        numberOfEnemiesPanel.add(numberOfEnemiesGrid, gbc_numberOfEnemiesPanel);

        gbc_numberOfEnemiesPanel.gridx = 0;
        gbc_numberOfEnemiesPanel.gridy = 2;
        numberOfEnemiesPanel.add(addSeparator(0, 70), gbc_numberOfEnemiesPanel);

        return numberOfEnemiesPanel;
    }

    /**
     * Method to create the button to start the attack. Has:
     * <p>
     * 1. Label that is inside the button.
     * 2. Battle icon to make the button visualization better.
     *
     * @return the JPanel with the attack button.
     */

    public JPanel startAttackButton() {

        // --------------------------- Start Attack Button -------------------------- //

        JImagePanel startAttackButton = new JImagePanel(SPRITE_ATTACK_BTN_BG);
        startAttackButton.setPreferredSize(new Dimension(270, 100));
        startAttackButton.setLayout(new GridBagLayout());
        startAttackButton.setOpaque(false);

        JLabel startAttackText = new JLabel();
        startAttackText.setText(LABEL_ATTACK_BTN);
        startAttackText.setForeground(Color.white);
        startAttackText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        startAttackText.setFont(fontStartAttack);

        JImagePanel iconAttack = new JImagePanel(SPRITE_ATTACK_ICO);
        iconAttack.setPreferredSize(new Dimension(35, 35));
        iconAttack.setOpaque(false);

        GridBagConstraints gbc_attackBtn = new GridBagConstraints();
        gbc_attackBtn.fill = GridBagConstraints.HORIZONTAL;

        gbc_attackBtn.gridx = 0;
        gbc_attackBtn.gridy = 0;
        gbc_attackBtn.gridwidth = 3;
        startAttackButton.add(startAttackText, gbc_attackBtn);

        gbc_attackBtn.gridx = 3;
        gbc_attackBtn.gridy = 0;
        gbc_attackBtn.gridwidth = 1;
        startAttackButton.add(iconAttack, gbc_attackBtn);

        return startAttackButton;
    }

    /**
     * Method that create the right panel of the setup stage view
     * calling method declared before. It has:
     * <p>
     * 1. The preview panel of the ship. (To see in which position is rotated).
     * 2. The number of enemies panel. (To select the number of enemies in game).
     * 3. The button to start the game.
     *
     * @return the JPanel with all the panels inside it.
     */

    public JPanel rightPanel() {

        // --------------------------- Right Panel -------------------------- //

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setOpaque(false);

        // Display all the panels inside the right panel.

        GridBagConstraints gbc_right = new GridBagConstraints();
        gbc_right.fill = GridBagConstraints.HORIZONTAL;

        gbc_right.gridx = 0;
        gbc_right.gridy = 0;
        rightPanel.add(previewPanel(), gbc_right);

        gbc_right.gridx = 0;
        gbc_right.gridy = 1;
        rightPanel.add(numberOfEnemiesPanel(), gbc_right);

        gbc_right.gridx = 0;
        gbc_right.gridy = 2;
        rightPanel.add(startAttackButton(), gbc_right);

        return rightPanel;
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

    public JPanel addSeparator(int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box.createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();
        switch (event) {
            case "boat":
                shipImage.switchImage(SPRITE_BOAT);
                shipImage.setPreferredSize(new Dimension());
                break;
            case "submarine":
                shipImage.switchImage(SPRITE_SUBMARINE);
                break;
            case "destructor":
                shipImage.switchImage(SPRITE_DESTRUCTOR);
                break;
            case "aircraft":
                shipImage.switchImage(SPRITE_AIRCRAFT);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
