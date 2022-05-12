package presentation.views;

import business.entities.Board;
import business.entities.Tile;
import business.entities.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class SetupStageView extends JPanel implements MouseListener {

    private final MainView mainView;

    private ShipPanel boatPanel;
    private ShipPanel submarinePanel1;
    private ShipPanel submarinePanel2;
    private ShipPanel destructorPanel;
    private ShipPanel aircraftPanel;

    private JImagePanel rotateButton;
    private JImagePanel shipImage;

    private JImagePanel one_enemies;
    private JImagePanel two_enemies;
    private JImagePanel three_enemies;
    private JImagePanel four_enemies;

    private int numberOfEnemies;
    private String orientation;
    private String shipSelected;
    private JImagePanel startAttackButton;


    // Image Path

    private final String SPRITE_WATER = "sprites/GameViews/water.png";
    private final String SPRITE_YOUR_SHIPS_BG = "sprites/GameViews/your_ships_panel.png";

    private final String SPRITE_BOAT = "sprites/GameViews/SetupStageView/rotated_boat.png";
    private final String SPRITE_SUBMARINE = "sprites/GameViews/SetupStageView/rotated_submarine.png";
    private final String SPRITE_AIRCRAFT = "sprites/GameViews/SetupStageView/rotated_aircraft.png";
    private final String SPRITE_DESTRUCTOR = "sprites/GameViews/SetupStageView/rotated_destructor.png";

    private final String SPRITE_SHIP_PANEL_BG = "sprites/ship_panel.png";
    private final String SPRITE_SHIP_PANEL_BG_HOVER = "sprites/ship_panel_hover.png";

    private final String SPRITE_BG2 = "sprites/GameViews/SetupStageView/bg2_panel.png";
    private final String SPRITE_ROTATE_BTN_BG = "sprites/GameViews/SetupStageView/bg_rotate_btn.png";
    private final String SPRITE_ROTATE_BTN_BG_SELECTED = "sprites/GameViews/SetupStageView/bg_rotate_btn_selected.png";
    private final String SPRITE_ROTATE_ARROW_ICO = "sprites/GameViews/SetupStageView/rotated_arrow.png";

    private final String SPRITE_FULL_USER = "sprites/GameViews/SetupStageView/full_user.png";
    private final String SPRITE_EMPTY_USER = "sprites/GameViews/SetupStageView/empty_user.png";
    private final String SPRITE_FULL_USER_50 = "sprites/GameViews/SetupStageView/full_ico_user_50%.png";

    private final String SPRITE_ATTACK_BTN_BG = "sprites/GameViews/SetupStageView/start_attack_bg.png";
    private final String SPRITE_ATTACK_BTN_BG_SELECTED = "sprites/GameViews/SetupStageView/start_game_selected.png";
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
        this.numberOfEnemies = 1;

        // Default values for orientation and ship selected
        this.orientation = "horizontal";
        this.shipSelected = "Boat";

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

    public String getOrientation() {
        return orientation;
    }

    public String getShipSelected() {
        return shipSelected;
    }

    private void initializeListeners() {
        boatPanel.addMouseListener(this);
        submarinePanel1.addMouseListener(this);
        submarinePanel2.addMouseListener(this);
        destructorPanel.addMouseListener(this);
        aircraftPanel.addMouseListener(this);
        rotateButton.addMouseListener(this);

        one_enemies.addMouseListener(this);
        two_enemies.addMouseListener(this);
        three_enemies.addMouseListener(this);
        four_enemies.addMouseListener(this);

        startAttackButton.addMouseListener(this);
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

        boatPanel = new ShipPanel("Boat", SPRITE_BOAT, SPRITE_SHIP_PANEL_BG, 60, 25);
        boatPanel.setName("boat");
        submarinePanel1 = new ShipPanel("Submarine 1", SPRITE_SUBMARINE, SPRITE_SHIP_PANEL_BG, 80, 25);
        submarinePanel1.setName("submarine1");
        submarinePanel2 = new ShipPanel("Submarine 2", SPRITE_SUBMARINE, SPRITE_SHIP_PANEL_BG,80, 25);
        submarinePanel2.setName("submarine2");
        destructorPanel = new ShipPanel("Destructor", SPRITE_DESTRUCTOR, SPRITE_SHIP_PANEL_BG,100, 25);
        destructorPanel.setName("destructor");
        aircraftPanel = new ShipPanel("Aircraft", SPRITE_AIRCRAFT, SPRITE_SHIP_PANEL_BG, 120, 25);
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
                table[i][j].setName("cell");
                // Nos interesa que el evento se active con el controlador.
                // table[i][j].addMouseListener(this);
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

        shipImage = new JImagePanel(SPRITE_BOAT, 0.2F, true);
        shipImage.setPreferredSize(new Dimension(180, 80));
        shipImage.setOpaque(false);

        // Button to rotate the selected ship.

        rotateButton = new JImagePanel(SPRITE_ROTATE_BTN_BG);
        rotateButton.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        rotateButton.setPreferredSize(new Dimension(200, 45));
        rotateButton.setOpaque(false);
        rotateButton.setName("rotate");

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
        shipPreviewPanel.add(addSeparator(0, 0), gbc_shipPreview);

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

        one_enemies = new JImagePanel(SPRITE_FULL_USER);
        one_enemies.setPreferredSize(new Dimension(30, 30));
        one_enemies.setOpaque(false);
        one_enemies.setName("one_enemies");

        // Enemy icon number 2. (Is empty, can be selected).

        two_enemies = new JImagePanel(SPRITE_EMPTY_USER);
        two_enemies.setPreferredSize(new Dimension(30, 30));
        two_enemies.setOpaque(false);
        two_enemies.setName("two_enemies");

        // Enemy icon number 3. (Is empty, can be selected).

        three_enemies = new JImagePanel(SPRITE_EMPTY_USER);
        three_enemies.setPreferredSize(new Dimension(30, 30));
        three_enemies.setOpaque(false);
        three_enemies.setName("three_enemies");

        // Enemy icon number 4. (Is empty, can be selected).

        four_enemies = new JImagePanel(SPRITE_EMPTY_USER);
        four_enemies.setPreferredSize(new Dimension(30, 30));
        four_enemies.setOpaque(false);
        four_enemies.setName("four_enemies");

        // Display all the enemies icons with separators panels between them.

        GridBagConstraints gbc_numberOfEnemiesGrid = new GridBagConstraints();
        gbc_numberOfEnemiesGrid.gridx = 0;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(one_enemies, gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 1;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(addSeparator(10, 0), gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 2;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(two_enemies, gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 3;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(addSeparator(10, 0), gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 4;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(three_enemies, gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 5;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(addSeparator(10, 0), gbc_numberOfEnemiesGrid);

        gbc_numberOfEnemiesGrid.gridx = 6;
        gbc_numberOfEnemiesGrid.gridy = 0;
        numberOfEnemiesGrid.add(four_enemies, gbc_numberOfEnemiesGrid);

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

        startAttackButton = new JImagePanel(SPRITE_ATTACK_BTN_BG);
        startAttackButton.setPreferredSize(new Dimension(270, 100));
        startAttackButton.setLayout(new GridBagLayout());
        startAttackButton.setOpaque(false);
        startAttackButton.setName("start_attack_button");

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
            case "boat" -> {
                shipImage.setScale(0.2F);
                shipImage.switchImage(SPRITE_BOAT);
                shipSelected = "Boat";
            }
            case "submarine1" -> {
                shipImage.setScale(0.15F);
                shipImage.switchImage(SPRITE_SUBMARINE);
                shipSelected = "Submarine1";
            }
            case "submarine2" -> {
                shipImage.setScale(0.15F);
                shipImage.switchImage(SPRITE_SUBMARINE);
                shipSelected = "Submarine2";
            }
            case "destructor" -> {
                shipImage.setScale(0.12F);
                shipImage.switchImage(SPRITE_DESTRUCTOR);
                shipSelected = "Destructor";
            }
            case "aircraft" -> {
                shipImage.setScale(0.12F);
                shipImage.switchImage(SPRITE_AIRCRAFT);
                shipSelected = "Aircraft";
            }
            case "rotate" -> {
                shipImage.rotateImage();
                System.out.println(shipImage.getWidth() + ", " + shipImage.getHeight());
                if (orientation.equals("vertical")) {
                    orientation = "horizontal";
                } else {
                    orientation = "vertical";
                }
            }
            case "one_enemies" -> {
                two_enemies.switchImage(SPRITE_EMPTY_USER);
                three_enemies.switchImage(SPRITE_EMPTY_USER);
                four_enemies.switchImage(SPRITE_EMPTY_USER);
                numberOfEnemies = 1;
            }
            case "two_enemies" -> {
                two_enemies.switchImage(SPRITE_FULL_USER);
                three_enemies.switchImage(SPRITE_EMPTY_USER);
                four_enemies.switchImage(SPRITE_EMPTY_USER);
                numberOfEnemies = 2;
            }
            case "three_enemies" -> {
                two_enemies.switchImage(SPRITE_FULL_USER);
                three_enemies.switchImage(SPRITE_FULL_USER);
                four_enemies.switchImage(SPRITE_EMPTY_USER);
                numberOfEnemies = 3;
            }
            case "four_enemies" -> {
                two_enemies.switchImage(SPRITE_FULL_USER);
                three_enemies.switchImage(SPRITE_FULL_USER);
                four_enemies.switchImage(SPRITE_FULL_USER);
                numberOfEnemies = 4;
            }
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
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "two_enemies":
                if (numberOfEnemies == 1) {
                    two_enemies.switchImage(SPRITE_FULL_USER_50);
                    three_enemies.switchImage(SPRITE_EMPTY_USER);
                    four_enemies.switchImage(SPRITE_EMPTY_USER);
                }
                break;
            case "three_enemies":
                if (numberOfEnemies == 1) {
                    two_enemies.switchImage(SPRITE_FULL_USER_50);
                    three_enemies.switchImage(SPRITE_FULL_USER_50);
                    four_enemies.switchImage(SPRITE_EMPTY_USER);
                } else if (numberOfEnemies == 2) {
                    three_enemies.switchImage(SPRITE_FULL_USER_50);
                    four_enemies.switchImage(SPRITE_EMPTY_USER);
                }
                break;
            case "four_enemies":
                if (numberOfEnemies == 1) {
                    two_enemies.switchImage(SPRITE_FULL_USER_50);
                    three_enemies.switchImage(SPRITE_FULL_USER_50);
                    four_enemies.switchImage(SPRITE_FULL_USER_50);
                } else if (numberOfEnemies == 2) {
                    three_enemies.switchImage(SPRITE_FULL_USER_50);
                    four_enemies.switchImage(SPRITE_FULL_USER_50);
                } else if (numberOfEnemies == 3) {
                    four_enemies.switchImage(SPRITE_FULL_USER_50);
                }
                break;
            case "start_attack_button":
                startAttackButton.switchImage(SPRITE_ATTACK_BTN_BG_SELECTED);
                break;
            case "rotate":
                rotateButton.switchImage(SPRITE_ROTATE_BTN_BG_SELECTED);
                break;

            case "boat":
                boatPanel.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG_HOVER);
                break;
            case "submarine1":
                submarinePanel1.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG_HOVER);
                break;
            case "submarine2":
                submarinePanel2.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG_HOVER);
                break;
            case "destructor":
                destructorPanel.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG_HOVER);
                break;
            case "aircraft":
                aircraftPanel.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG_HOVER);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "two_enemies":
                if (numberOfEnemies == 1) {
                    two_enemies.switchImage(SPRITE_EMPTY_USER);
                }
                break;
            case "three_enemies":
                if (numberOfEnemies == 1) {
                    two_enemies.switchImage(SPRITE_EMPTY_USER);
                    three_enemies.switchImage(SPRITE_EMPTY_USER);
                }
                if (numberOfEnemies == 2) {
                    three_enemies.switchImage(SPRITE_EMPTY_USER);
                }
                break;
            case "four_enemies":
                if (numberOfEnemies == 1) {
                    two_enemies.switchImage(SPRITE_EMPTY_USER);
                    three_enemies.switchImage(SPRITE_EMPTY_USER);
                    four_enemies.switchImage(SPRITE_EMPTY_USER);
                } else if (numberOfEnemies == 2) {
                    three_enemies.switchImage(SPRITE_EMPTY_USER);
                    four_enemies.switchImage(SPRITE_EMPTY_USER);
                } else if (numberOfEnemies == 3) {
                    four_enemies.switchImage(SPRITE_EMPTY_USER);
                }
                break;
            case "start_attack_button":
                startAttackButton.switchImage(SPRITE_ATTACK_BTN_BG);
                break;
            case "rotate":
                rotateButton.switchImage(SPRITE_ATTACK_BTN_BG);
                break;

            case "boat":
                boatPanel.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG);
                break;
            case "submarine1":
                submarinePanel1.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG);
                break;
            case "submarine2":
                submarinePanel2.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG);
                break;
            case "destructor":
                destructorPanel.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG);
                break;
            case "aircraft":
                aircraftPanel.getBackgroundPanel().switchImage(SPRITE_SHIP_PANEL_BG);
                break;
        }
    }

    public void registerController(MouseListener mouseListener) {
        for (Cell[] cells : table) {
            for (Cell cell : cells) {
                cell.addMouseListener(mouseListener);
            }
        }
        startAttackButton.addMouseListener(mouseListener);
    }

    public void paintShip(Board board) {

        Tile[][] tiles = board.getTiles();

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                TileType status = tiles[i][j].getTileType();
                if (status == TileType.SHIP) {
                    table[i][j].switchImage(SPRITE_FULL_USER_50);
                } else if (status == TileType.HIT) {
                    table[i][j].switchImage(SPRITE_FULL_USER);
                } else if (status == TileType.WATER) {
                    table[i][j].switchImage(SPRITE_WATER);
                }
            }
        }

    }
}
