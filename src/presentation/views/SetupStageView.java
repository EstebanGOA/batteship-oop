package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SetupStageView extends JFrame {

    // Image Path

    private final String SPRITE_WATER = "sprites/water.png";

    // Background Color

    private final Color BACKGROUND_COLOR = new Color(39,152,213);
    private final Color PANEL_COLOR = new Color(33,135,201);

    // Font Path

    private final String FONT_BLACK = "fonts/Poppins-Black.ttf";
    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";

    public SetupStageView () {
        initializeWindow();

        Font fontStartAttack = initializeFont (FONT_BLACK, 20F);
        Font fontPanelTitle  = initializeFont (FONT_BOLD, 18F);

        // ------------------------ Background Image ------------------------ //

        JPanel backgroundPanel = new JPanel();
            backgroundPanel.setBackground(BACKGROUND_COLOR);
            backgroundPanel.setLayout(new GridBagLayout());

        // --------------------------- Left Panel -------------------------- //

        JImagePanel leftPanel = new JImagePanel("sprites/your_ships_panel.png");
            leftPanel.setLayout(new GridBagLayout());
            leftPanel.setPreferredSize(new Dimension (300, 670));
            leftPanel.setOpaque(false);

            JLabel yourShipsText = new JLabel();
                yourShipsText.setText("Your Ships");
                yourShipsText.setForeground(Color.white);
                yourShipsText.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
                yourShipsText.setFont(fontPanelTitle);

            ShipPanel boatPanel       = new ShipPanel ("Boat"       , "sprites/boat.png");
            ShipPanel submarinePanel1 = new ShipPanel ("Submarine 1", "sprites/boat.png");
            ShipPanel submarinePanel2 = new ShipPanel ("Submarine 2", "sprites/boat.png");
            ShipPanel aircraftPanel   = new ShipPanel ("Aircraft"   , "sprites/boat.png");
            ShipPanel destructorPanel = new ShipPanel ("Destructor" , "sprites/boat.png");

            // TODO COMO HACEMOS PARA ROTAR UNA IMAGEN?

        GridBagConstraints gbc_left = new GridBagConstraints();
            gbc_left.gridx = 0; gbc_left.gridy = 0;
            leftPanel.add(yourShipsText, gbc_left);

            gbc_left.gridx = 0; gbc_left.gridy = 1;
            leftPanel.add(boatPanel, gbc_left);

            gbc_left.gridx = 0; gbc_left.gridy = 2;
            leftPanel.add(submarinePanel1, gbc_left);

            gbc_left.gridx = 0; gbc_left.gridy = 3;
            leftPanel.add(submarinePanel2, gbc_left);

            gbc_left.gridx = 0; gbc_left.gridy = 4;
            leftPanel.add(aircraftPanel, gbc_left);

            gbc_left.gridx = 0; gbc_left.gridy = 5;
            leftPanel.add(destructorPanel, gbc_left);

            gbc_left.gridx = 0; gbc_left.gridy = 6;
            leftPanel.add(addSeparator(0, 15), gbc_left);

        // ----------------------------- Table ---------------------------- //

        JPanel tableGrid = new JPanel();
            tableGrid.setLayout(new GridLayout(15,15));
            tableGrid.setPreferredSize(new Dimension(650,650));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tableGrid.add(new JImagePanel(SPRITE_WATER));
            }
        }

        GridBagConstraints gbc_table = new GridBagConstraints();

        // --------------------------- Right Panel -------------------------- //

        JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new GridBagLayout());
            rightPanel.setOpaque(false);

            JImagePanel shipPreviewPanel = new JImagePanel("sprites/bg2_panel.png");
                shipPreviewPanel.setPreferredSize(new Dimension (270, 280));
                shipPreviewPanel.setLayout(new GridBagLayout());
                shipPreviewPanel.setOpaque(false);

                JLabel shipPreviewText = new JLabel();
                    shipPreviewText.setText("Ship Preview");
                    shipPreviewText.setForeground(Color.white);
                    shipPreviewText.setBorder(BorderFactory.createEmptyBorder(10,35,35,0));
                    shipPreviewText.setFont(fontPanelTitle);

                JImagePanel shipImage = new JImagePanel ("sprites/boat.png");
                    shipImage.setPreferredSize(new Dimension(40,80));
                    shipImage.setOpaque(false);

                JImagePanel rotateButton = new JImagePanel("sprites/bg_rotate_btn.png");
                    rotateButton.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
                    rotateButton.setPreferredSize(new Dimension(200,45));
                    rotateButton.setOpaque(false);

                    JLabel rotateButtonText = new JLabel();
                        rotateButtonText.setText("ROTATE");
                        rotateButtonText.setForeground(Color.white);
                        rotateButtonText.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
                        rotateButtonText.setFont(fontPanelTitle);

                    JImagePanel rotateIcon = new JImagePanel("sprites/rotated_arrow.png");
                        rotateIcon.setPreferredSize(new Dimension(25,25));
                        rotateIcon.setOpaque(false);

                    GridBagConstraints gbc_rotateBtn = new GridBagConstraints();
                        gbc_rotateBtn.fill = GridBagConstraints.HORIZONTAL;

                        gbc_rotateBtn.gridx = 0; gbc_rotateBtn.gridy = 0;
                        gbc_rotateBtn.gridwidth = 3;
                        rotateButton.add(rotateButtonText, gbc_rotateBtn);

                        gbc_rotateBtn.gridx = 3; gbc_rotateBtn.gridy = 0;
                        gbc_rotateBtn.gridwidth = 1;
                        rotateButton.add(rotateIcon, gbc_rotateBtn);

                GridBagConstraints gbc_shipPreview = new GridBagConstraints();
                    gbc_shipPreview.fill = GridBagConstraints.HORIZONTAL;

                    gbc_shipPreview.gridx = 0; gbc_shipPreview.gridy = 0;
                    gbc_shipPreview.gridwidth = 3;
                    shipPreviewPanel.add(shipPreviewText, gbc_shipPreview);

                    gbc_shipPreview.gridx = 0; gbc_shipPreview.gridy = 1;
                    gbc_shipPreview.gridwidth = 1;
                    shipPreviewPanel.add(addSeparator(75, 0), gbc_shipPreview);

                    gbc_shipPreview.gridx = 1; gbc_shipPreview.gridy = 1;
                    gbc_shipPreview.gridwidth = 1;
                    shipPreviewPanel.add(shipImage, gbc_shipPreview);

                    gbc_shipPreview.gridx = 0; gbc_shipPreview.gridy = 2;
                    gbc_shipPreview.gridwidth = 3;
                    shipPreviewPanel.add(addSeparator(0, 20), gbc_shipPreview);

                    gbc_shipPreview.gridx = 0; gbc_shipPreview.gridy = 3;
                    gbc_shipPreview.gridwidth = 3;
                    shipPreviewPanel.add(rotateButton, gbc_shipPreview);

            // --------------------------- Number of Enemies Panel -------------------------- //

            JImagePanel numberOfEnemiesPanel = new JImagePanel("sprites/bg2_panel.png");
                numberOfEnemiesPanel.setPreferredSize(new Dimension (270, 280));
                numberOfEnemiesPanel.setLayout(new GridBagLayout());
                numberOfEnemiesPanel.setOpaque(false);

                JLabel numberOfEnemiesText = new JLabel();
                    numberOfEnemiesText.setText("Number of Enemies");
                    numberOfEnemiesText.setForeground(Color.white);
                    numberOfEnemiesText.setBorder(BorderFactory.createEmptyBorder(0,0,80,0));
                    numberOfEnemiesText.setFont(fontPanelTitle);

                JPanel numberOfEnemiesGrid = new JPanel();
                    numberOfEnemiesGrid.setLayout(new GridBagLayout());
                    numberOfEnemiesGrid.setOpaque(false);

                    JImagePanel enemy1 = new JImagePanel("sprites/full_user.png");
                        enemy1.setPreferredSize(new Dimension(30,30));
                        enemy1.setOpaque(false);
                    JImagePanel enemy2 = new JImagePanel("sprites/empty_user.png");
                        enemy2.setPreferredSize(new Dimension(30,30));
                        enemy2.setOpaque(false);
                    JImagePanel enemy3 = new JImagePanel("sprites/empty_user.png");
                        enemy3.setPreferredSize(new Dimension(30,30));
                        enemy3.setOpaque(false);
                    JImagePanel enemy4 = new JImagePanel("sprites/empty_user.png");
                        enemy4.setPreferredSize(new Dimension(30,30));
                        enemy4.setOpaque(false);

                GridBagConstraints gbc_numberOfEnemiesGrid = new GridBagConstraints();
                    gbc_numberOfEnemiesGrid.gridx = 0; gbc_numberOfEnemiesGrid.gridy = 0;
                    numberOfEnemiesGrid.add(enemy1, gbc_numberOfEnemiesGrid);

                    gbc_numberOfEnemiesGrid.gridx = 1; gbc_numberOfEnemiesGrid.gridy = 0;
                    numberOfEnemiesGrid.add(addSeparator(10,0), gbc_numberOfEnemiesGrid);

                    gbc_numberOfEnemiesGrid.gridx = 2; gbc_numberOfEnemiesGrid.gridy = 0;
                    numberOfEnemiesGrid.add(enemy2, gbc_numberOfEnemiesGrid);

                    gbc_numberOfEnemiesGrid.gridx = 3; gbc_numberOfEnemiesGrid.gridy = 0;
                    numberOfEnemiesGrid.add(addSeparator(10,0), gbc_numberOfEnemiesGrid);

                    gbc_numberOfEnemiesGrid.gridx = 4; gbc_numberOfEnemiesGrid.gridy = 0;
                    numberOfEnemiesGrid.add(enemy3, gbc_numberOfEnemiesGrid);

                    gbc_numberOfEnemiesGrid.gridx = 5; gbc_numberOfEnemiesGrid.gridy = 0;
                    numberOfEnemiesGrid.add(addSeparator(10,0), gbc_numberOfEnemiesGrid);

                    gbc_numberOfEnemiesGrid.gridx = 6; gbc_numberOfEnemiesGrid.gridy = 0;
                    numberOfEnemiesGrid.add(enemy4, gbc_numberOfEnemiesGrid);


            GridBagConstraints gbc_numberOfEnemiesPanel = new GridBagConstraints();
                gbc_numberOfEnemiesPanel.gridx = 0; gbc_numberOfEnemiesPanel.gridy = 0;
                numberOfEnemiesPanel.add(numberOfEnemiesText, gbc_numberOfEnemiesPanel);

                gbc_numberOfEnemiesPanel.gridx = 0; gbc_numberOfEnemiesPanel.gridy = 1;
                numberOfEnemiesPanel.add(numberOfEnemiesGrid, gbc_numberOfEnemiesPanel);

                gbc_numberOfEnemiesPanel.gridx = 0; gbc_numberOfEnemiesPanel.gridy = 2;
                numberOfEnemiesPanel.add(addSeparator(0,70), gbc_numberOfEnemiesPanel);

            // --------------------------- Start Attack Button -------------------------- //

            JImagePanel startAttackButton = new JImagePanel("sprites/start_attack_bg.png");
                startAttackButton.setPreferredSize(new Dimension (270, 100));
                startAttackButton.setLayout(new GridBagLayout());
                startAttackButton.setOpaque(false);

                JLabel startAttackText = new JLabel();
                    startAttackText.setText("START ATTACK");
                    startAttackText.setForeground(Color.white);
                    startAttackText.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
                    startAttackText.setFont(fontStartAttack);

                JImagePanel iconAttack = new JImagePanel("sprites/attack_icon.png");
                    iconAttack.setOpaque(false);
                    iconAttack.setPreferredSize(new Dimension(35,35));

                GridBagConstraints gbc_attackBtn = new GridBagConstraints();
                    gbc_attackBtn.fill = GridBagConstraints.HORIZONTAL;

                    gbc_attackBtn.gridx = 0; gbc_attackBtn.gridy = 0;
                    gbc_attackBtn.gridwidth = 3;
                    startAttackButton.add(startAttackText, gbc_attackBtn);

                    gbc_attackBtn.gridx = 3; gbc_attackBtn.gridy = 0;
                    gbc_attackBtn.gridwidth = 1;
                    startAttackButton.add(iconAttack, gbc_attackBtn);

        GridBagConstraints gbc_right = new GridBagConstraints();
            gbc_right.fill = GridBagConstraints.HORIZONTAL;

            gbc_right.gridx = 0; gbc_right.gridy = 0;
            rightPanel.add(shipPreviewPanel, gbc_right);

            gbc_right.gridx = 0; gbc_right.gridy = 1;
            rightPanel.add(numberOfEnemiesPanel, gbc_right);

            gbc_right.gridx = 0; gbc_right.gridy = 2;
            rightPanel.add(startAttackButton, gbc_right);

        GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            backgroundPanel.add(leftPanel, gbc);

            gbc.gridx = 1; gbc.gridy = 0;
            backgroundPanel.add(addSeparator(10,0));

            gbc.gridx = 2; gbc.gridy = 0;
            backgroundPanel.add(tableGrid, gbc);

            gbc.gridx = 3; gbc.gridy = 0;
            backgroundPanel.add(addSeparator(10,0));

            gbc.gridx = 4; gbc.gridy = 0;
            backgroundPanel.add(rightPanel, gbc);

        add(backgroundPanel);

        setVisible(true);
    }

    public void initializeWindow () {
        setSize(1280, 720);

        setTitle("Battleship v1.0.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Font initializeFont (String fontPath, float fontSize) {
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

    public JPanel addSeparator (int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box. createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }
}
