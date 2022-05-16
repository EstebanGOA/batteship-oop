package presentation.views;

import business.entities.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameStageView extends JPanel implements MouseListener {

    private final MainView mainView;

    private final String SPRITE_YOUR_SHIPS_BG = "sprites/GameViews/your_ships_panel.png";

    private final String SPRITE_SHIP_PANEL_BG = "sprites/ship_panel.png";

    private final String SPRITE_BOAT = "sprites/GameViews/SetupStageView/rotated_boat.png";
    private final String SPRITE_SUBMARINE = "sprites/GameViews/SetupStageView/rotated_submarine.png";
    private final String SPRITE_AIRCRAFT = "sprites/GameViews/SetupStageView/rotated_aircraft.png";
    private final String SPRITE_DESTRUCTOR = "sprites/GameViews/SetupStageView/rotated_destructor.png";

    private final String SPRITE_WATER = "sprites/GameViews/water.png";
    private final String SPRITE_WATER_HOVER = "sprites/GameViews/water_hover.png";

    private final String SPRITE_END_BATTLE_BG = "sprites/endBattleBg.png";
    private final String SPRITE_END_BATTLE_BTN_HOVER = "sprites/endBattleBtn_hover.png";
    private final String SPRITE_END_BATTLE_BTN = "sprites/endBattleBtn.png";

    private final Color BACKGROUND_COLOR = new Color(39,152,213);

    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";

    private final Font fontPanelTitle = initializeFont (FONT_BOLD, 18F);
    private final Font fontEndBattleTexts = initializeFont (FONT_BOLD, 25F);

    private JImagePanel endBattleBtn;

    private Cell[][] table = new Cell[15][15];

    /**
     *
     * Constructor method for the GameStageView Panel
     *
     * @param mainView class to use the card controller and the listeners.
     *
     */

    public GameStageView (MainView mainView) {
        this.mainView = mainView;

        JPanel bg = setBackground();
            bg.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0; gbc.gridy = 0;
            bg.add(yourShipsPanel(), gbc);

            gbc.gridx = 1; gbc.gridy = 0;
            bg.add(tableAndEndBattlePanel(), gbc);

            gbc.gridx = 2; gbc.gridy = 0;
            bg.add(rightPanel(), gbc);

        initializeListeners();
        add(bg);
    }

    public void initializeListeners () {
        endBattleBtn.addMouseListener(this);
    }

    /**
     *
     * Method to set the background of the View.
     *
     * @return the JPanel with the background view.
     *
     */

    public JPanel setBackground () {
        JPanel p = new JPanel();
            p.setBackground(BACKGROUND_COLOR);
            p.setLayout(new BorderLayout());
            p.setPreferredSize(new Dimension(1280, 720));
        return p;
    }

    /**
     *
     * Method to create the player ships status panel.
     *
     * Is a JPanel with inside a JTable with 2 columns and 5 rows as required.
     *      - The first column has the name and the image of the ship and
     *      - The second column has the status of the player ship.
     *
     * @return the JPanel with all the player ships status.
     *
     */

    public JPanel yourShipsPanel () {

        // --------------------------- Left Panel -------------------------- //

        JImagePanel leftPanel = new JImagePanel (SPRITE_YOUR_SHIPS_BG);
            leftPanel.setLayout(new GridBagLayout());
            leftPanel.setPreferredSize(new Dimension(300, 670));
            leftPanel.setOpaque(false);

            // Label with the title of the panel

            JLabel yourShipsText = new JLabel();
                yourShipsText.setText ("Your Ships Status");
                yourShipsText.setForeground(Color.white);
                yourShipsText.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
                yourShipsText.setFont (fontPanelTitle);

            // Setting the ArrayList with all the status ships

            ArrayList<ShipPanel> ships = new ArrayList<>();

            ships.add(new ShipPanel("Boat", SPRITE_BOAT, SPRITE_SHIP_PANEL_BG, 60, 25, 125));
            ships.add(new ShipPanel("Submarine 1", SPRITE_SUBMARINE, SPRITE_SHIP_PANEL_BG, 70, 25, 125));
            ships.add(new ShipPanel("Submarine 2",  SPRITE_SUBMARINE, SPRITE_SHIP_PANEL_BG,70, 25, 125));
            ships.add(new ShipPanel("Destructor", SPRITE_DESTRUCTOR, SPRITE_SHIP_PANEL_BG,70, 25, 125));
            ships.add(new ShipPanel("Aircraft", SPRITE_AIRCRAFT, SPRITE_SHIP_PANEL_BG, 70, 25, 125));

            ArrayList<JShipStatus> shipsStatus = new ArrayList<>();

            shipsStatus.add(new JShipStatus("Intact", 125));
            shipsStatus.add(new JShipStatus("Intact", 125));
            shipsStatus.add(new JShipStatus("Intact", 125));
            shipsStatus.add(new JShipStatus("Intact", 125));
            shipsStatus.add(new JShipStatus("Intact", 125));

            // Insert the arraylist in the JTable

            JTable shipsStatusTable = new JTable (new YourShipsTableModel ());
                shipsStatusTable.setDefaultRenderer(ShipPanel.class, new YourShipsTableRenderer(ships, shipsStatus));
                shipsStatusTable.setDefaultRenderer(JShipStatus.class, new YourShipsTableRenderer(ships, shipsStatus));
                shipsStatusTable.setRowHeight(100);
                shipsStatusTable.getColumnModel().getColumn(0).setPreferredWidth(115);
                shipsStatusTable.getColumnModel().getColumn(1).setPreferredWidth(115);
                shipsStatusTable.setOpaque(false);
                shipsStatusTable.setShowGrid(false);
                shipsStatusTable.setShowHorizontalLines(false);
                shipsStatusTable.setShowVerticalLines(false);

        // Display all the things inside the left panel.

        GridBagConstraints gbc_left = new GridBagConstraints();
            gbc_left.gridx = 0;
            gbc_left.gridy = 0;
            leftPanel.add(yourShipsText, gbc_left);

            gbc_left.gridx = 0;
            gbc_left.gridy = 1;
            leftPanel.add(shipsStatusTable, gbc_left);

            gbc_left.gridx = 0;
            gbc_left.gridy = 2;
            leftPanel.add(new JSeparator(0,25), gbc_left);

        return leftPanel;
    }

    /**
     *
     * Method to create the endBattlePanel and the player table explained both below.
     * Is a grid bag layout, so we can position the end battle panel, a separator and the table.
     *
     * @return the JPanel with the endBattlePanel and the player table.
     *
     */

    public JPanel tableAndEndBattlePanel () {
        JPanel tableAndEndBattlePanel = new JPanel();
            tableAndEndBattlePanel.setOpaque(false);
            tableAndEndBattlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc_center = new GridBagConstraints();
            gbc_center.gridx = 0;
            gbc_center.gridy = 0;
            tableAndEndBattlePanel.add(endBattlePanel (), gbc_center);

            gbc_center.gridx = 0;
            gbc_center.gridy = 1;
            tableAndEndBattlePanel.add(new JSeparator(0,20), gbc_center);

            gbc_center.gridx = 0;
            gbc_center.gridy = 2;
            tableAndEndBattlePanel.add(table (), gbc_center);

            gbc_center.gridx = 0;
            gbc_center.gridy = 3;
            tableAndEndBattlePanel.add(new JSeparator(0,10), gbc_center);

        return tableAndEndBattlePanel;
    }

    /**
     *
     * Method to create the EndBattlePanel which is made up of:
     *      - The button to end the battle.
     *      - The duration time of the game.
     *      - The status of the game (If you can attack, or you have to wait...).
     *
     * @return
     */

    public JPanel endBattlePanel () {
        JImagePanel endBattlePanel = new JImagePanel(SPRITE_END_BATTLE_BG);
            endBattlePanel.setOpaque(false);
            endBattlePanel.setLayout(new GridBagLayout());
            endBattlePanel.setPreferredSize(new Dimension(480,170));

            JLabel statusAttack = new JLabel();
                statusAttack.setText ("Attack");
                statusAttack.setForeground(Color.white);
                statusAttack.setFont (fontEndBattleTexts);
                statusAttack.setBorder(BorderFactory.createEmptyBorder(0, 25, 15, 0));

            JLabel gameTime = new JLabel();
                gameTime.setText ("00:00");
                gameTime.setForeground(Color.white);
                gameTime.setFont (fontEndBattleTexts);
                gameTime.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 25));

            endBattleBtn = new JImagePanel(SPRITE_END_BATTLE_BTN);
                endBattleBtn.setPreferredSize(new Dimension(380,70));
                endBattleBtn.setLayout(new BorderLayout());
                endBattleBtn.setOpaque(false);
                endBattleBtn.setName("endBattleBtn");

                JLabel endBattleText = new JLabel();
                    endBattleText.setText ("End Battle");
                    endBattleText.setForeground(Color.white);
                    endBattleText.setFont (fontPanelTitle);
                    endBattleText.setHorizontalAlignment(SwingConstants.CENTER);
                    endBattleText.setVerticalAlignment(SwingConstants.CENTER);

                endBattleBtn.add(endBattleText, BorderLayout.CENTER);

        GridBagConstraints gbc_endBattle = new GridBagConstraints();
            gbc_endBattle.gridx = 0;
            gbc_endBattle.gridy = 0;
            endBattlePanel.add(new JSeparator(0,0));

            gbc_endBattle.gridy = 1;
            gbc_endBattle.gridwidth = 1;
            gbc_endBattle.anchor = GridBagConstraints.FIRST_LINE_START;
            endBattlePanel.add(statusAttack, gbc_endBattle);

            gbc_endBattle.gridx = 1;
            gbc_endBattle.gridy = 1;
            gbc_endBattle.gridwidth = 1;
            gbc_endBattle.anchor = GridBagConstraints.FIRST_LINE_END;
            endBattlePanel.add(gameTime, gbc_endBattle);

            gbc_endBattle.gridx = 0;
            gbc_endBattle.gridy = 2;
            endBattlePanel.add(new JSeparator(0,0));

            gbc_endBattle.gridx = 0;
            gbc_endBattle.gridy = 3;
            gbc_endBattle.gridwidth = 2;
            endBattlePanel.add(endBattleBtn, gbc_endBattle);

        return endBattlePanel;
    }

    /**
     *
     * Method to create the table of the player
     *
     * @return the JPanel with all the cells of the player table.
     *
     */

    public JPanel table() {
        JPanel tableGrid = new JPanel();
        tableGrid.setLayout(new GridLayout(15, 15));
        tableGrid.setPreferredSize(new Dimension(450, 450));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                table[i][j] = new Cell(j, i, SPRITE_WATER);
                tableGrid.add(table[i][j]);
            }
        }

        return tableGrid;
    }

    public JPanel rightPanel () {
        JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new GridBagLayout ());
            rightPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0; gbc.gridy = 0;
            rightPanel.add(new JEnemy(), gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            rightPanel.add(new JSeparator(0,5), gbc);

            gbc.gridx = 0; gbc.gridy = 2;
            rightPanel.add(new JEnemy(), gbc);

            gbc.gridx = 0; gbc.gridy = 3;
            rightPanel.add(new JSeparator(0,5), gbc);

            gbc.gridx = 0; gbc.gridy = 4;
            rightPanel.add(new JEnemy(), gbc);

            gbc.gridx = 0; gbc.gridy = 5;
            rightPanel.add(new JSeparator(0,5), gbc);

            gbc.gridx = 0; gbc.gridy = 6;
            rightPanel.add(new JEnemy(), gbc);

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

    @Override
    public void mouseClicked(MouseEvent e) {

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
            case "endBattleBtn":
                endBattleBtn.switchImage(SPRITE_END_BATTLE_BTN_HOVER);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "endBattleBtn":
                endBattleBtn.switchImage(SPRITE_END_BATTLE_BTN);
                break;
        }
    }
}
