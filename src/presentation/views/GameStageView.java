package presentation.views;

import business.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameStageView extends JPanel implements MouseListener {

    private final MainView mainView;

    private final Color BACKGROUND_COLOR = new Color(39, 152, 213);

    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";

    private final Font fontPanelTitle = initializeFont(FONT_BOLD, 18F);
    private final Font fontEndBattleTexts = initializeFont(FONT_BOLD, 25F);

    private JImagePanel endBattleBtn;
    private JPanel backgound;
    private JLabel gameTime;
    JLabel statusAttack;

    private Cell[][] table = new Cell[15][15];
    private ArrayList<JEnemy> enemies = new ArrayList<>();
    private JShipStatus[] shipsStatus = new JShipStatus[5];
    private JTable shipsStatusTable;


    /**
     * Constructor method for the GameStageView Panel
     *
     * @param mainView class to use the card controller and the listeners.
     */

    public GameStageView(MainView mainView) {
        this.mainView = mainView;

        backgound = setBackground();
        backgound.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgound.add(yourShipsPanel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        backgound.add(tableAndEndBattlePanel(), gbc);

        initializeListeners();

    }

    /**
     *
     * Method to initialize the listeners of the view.
     *
     */

    public void initializeListeners() {
        endBattleBtn.addMouseListener(this);
    }

    /**
     * Method to set the background of the View.
     *
     * @return the JPanel with the background view.
     */

    public JPanel setBackground() {
        JPanel p = new JPanel();
        p.setBackground(BACKGROUND_COLOR);
        p.setLayout(new BorderLayout());
        p.setPreferredSize(new Dimension(1280, 720));
        return p;
    }

    /**
     * Method to create the player ships status panel.
     * <p>
     * Is a JPanel with inside a JTable with 2 columns and 5 rows as required.
     * - The first column has the name and the image of the ship and
     * - The second column has the status of the player ship.
     *
     * @return the JPanel with all the player ships status.
     */

    public JPanel yourShipsPanel() {

        // --------------------------- Left Panel -------------------------- //

        JImagePanel leftPanel = new JImagePanel(SpritePath.PLAYER_SHIPS_BACKGROUND);
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(300, 670));
        leftPanel.setOpaque(false);

        // Label with the title of the panel

        JLabel yourShipsText = new JLabel();
        yourShipsText.setText("Your Ships Status");
        yourShipsText.setForeground(Color.white);
        yourShipsText.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        yourShipsText.setFont(fontPanelTitle);

        // Setting the ArrayList with all the status ships

        ArrayList<ShipPanel> ships = new ArrayList<>();

        ships.add(new ShipPanel("Boat", SpritePath.BOAT, SpritePath.SHIP_PANEL_BACKGROUND, 60, 25, 125));
        ships.add(new ShipPanel("Submarine 1", SpritePath.SUBMARINE, SpritePath.SHIP_PANEL_BACKGROUND, 70, 25, 125));
        ships.add(new ShipPanel("Submarine 2", SpritePath.SUBMARINE, SpritePath.SHIP_PANEL_BACKGROUND, 70, 25, 125));
        ships.add(new ShipPanel("Destructor", SpritePath.DESTRUCTOR, SpritePath.SHIP_PANEL_BACKGROUND, 70, 25, 125));
        ships.add(new ShipPanel("Aircraft", SpritePath.AIRCRAFT, SpritePath.SHIP_PANEL_BACKGROUND, 70, 25, 125));

        shipsStatus[0] = new JShipStatus("Default text for ship status", 125);
        shipsStatus[1] = new JShipStatus("Default text for ship status", 125);
        shipsStatus[2] = new JShipStatus("Default text for ship status", 125);
        shipsStatus[3] = new JShipStatus("Default text for ship status", 125);
        shipsStatus[4] = new JShipStatus("Default text for ship status", 125);

        // Insert the arraylist in the JTable

        shipsStatusTable = new JTable(new YourShipsTableModel());
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
        leftPanel.add(new JSeparator(0, 25), gbc_left);

        return leftPanel;
    }

    /**
     * Method to create the endBattlePanel and the player table explained both below.
     * Is a grid bag layout, so we can position the end battle panel, a separator and the table.
     *
     * @return the JPanel with the endBattlePanel and the player table.
     */

    public JPanel tableAndEndBattlePanel() {
        JPanel tableAndEndBattlePanel = new JPanel();
        tableAndEndBattlePanel.setOpaque(false);
        tableAndEndBattlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc_center = new GridBagConstraints();
        gbc_center.gridx = 0;
        gbc_center.gridy = 0;
        tableAndEndBattlePanel.add(endBattlePanel(), gbc_center);

        gbc_center.gridx = 0;
        gbc_center.gridy = 1;
        tableAndEndBattlePanel.add(new JSeparator(0, 20), gbc_center);

        gbc_center.gridx = 0;
        gbc_center.gridy = 2;
        tableAndEndBattlePanel.add(table(), gbc_center);

        gbc_center.gridx = 0;
        gbc_center.gridy = 3;
        tableAndEndBattlePanel.add(new JSeparator(0, 10), gbc_center);

        return tableAndEndBattlePanel;
    }

    /**
     * Method to create the EndBattlePanel which is made up of:
     * - The button to end the battle.
     * - The duration time of the game.
     * - The status of the game (If you can attack, or you have to wait...).
     *
     * @return
     */

    public JPanel endBattlePanel() {
        JImagePanel endBattlePanel = new JImagePanel(SpritePath.END_BATTLE_BACKGROUND);
        endBattlePanel.setOpaque(false);
        endBattlePanel.setLayout(new GridBagLayout());
        endBattlePanel.setPreferredSize(new Dimension(480, 170));

        statusAttack = new JLabel();
        statusAttack.setText("Attack");
        statusAttack.setForeground(Color.white);
        statusAttack.setFont(fontEndBattleTexts);
        statusAttack.setBorder(BorderFactory.createEmptyBorder(0, 25, 15, 0));

        gameTime = new JLabel();
        gameTime.setText("00:00");
        gameTime.setForeground(Color.white);
        gameTime.setFont(fontEndBattleTexts);
        gameTime.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 25));

        endBattleBtn = new JImagePanel(SpritePath.END_BATTLE_BUTTON);
        endBattleBtn.setPreferredSize(new Dimension(380, 70));
        endBattleBtn.setLayout(new BorderLayout());
        endBattleBtn.setOpaque(false);
        endBattleBtn.setName("endBattleBtn");

        JLabel endBattleText = new JLabel();
        endBattleText.setText("End Battle");
        endBattleText.setForeground(Color.white);
        endBattleText.setFont(fontPanelTitle);
        endBattleText.setHorizontalAlignment(SwingConstants.CENTER);
        endBattleText.setVerticalAlignment(SwingConstants.CENTER);

        endBattleBtn.add(endBattleText, BorderLayout.CENTER);

        GridBagConstraints gbc_endBattle = new GridBagConstraints();
        gbc_endBattle.gridx = 0;
        gbc_endBattle.gridy = 0;
        endBattlePanel.add(new JSeparator(0, 0));

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
        endBattlePanel.add(new JSeparator(0, 0));

        gbc_endBattle.gridx = 0;
        gbc_endBattle.gridy = 3;
        gbc_endBattle.gridwidth = 2;
        endBattlePanel.add(endBattleBtn, gbc_endBattle);

        return endBattlePanel;
    }

    /**
     * Method to create the table of the player
     *
     * @return the JPanel with all the cells of the player table.
     */

    public JPanel table() {
        JPanel tableGrid = new JPanel();
        tableGrid.setLayout(new GridLayout(15, 15));
        tableGrid.setPreferredSize(new Dimension(450, 450));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                table[i][j] = new Cell(j, i, SpritePath.WATER);
                tableGrid.add(table[i][j]);
                table[i][j].setName("cell");
            }
        }

        return tableGrid;
    }

    /**
     *
     * Method that creates the right panel with the JEnemies panels (enemy table and enemy ships status).
     *
     * @param numberOfEnemies number of enemies.
     *
     * @return the right panel of the view.
     *
     */

    public JPanel rightPanel(int numberOfEnemies) {

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < numberOfEnemies * 2; i = i + 2) {

            gbc.gridx = 0;
            gbc.gridy = i;

            JEnemy jEnemy = new JEnemy();
            rightPanel.add(jEnemy, gbc);
            enemies.add(jEnemy);

            gbc.gridx = 0;
            gbc.gridy = (i + 1);
            rightPanel.add(new JSeparator(0, 5), gbc);

        }
        return rightPanel;

    }

    /**
     *
     * Method to initialize the font of the window.
     *
     * @param fontPath path of the font.
     * @param fontSize size of the font.
     *
     * @return the initialized font.
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
     * Method to check if the mouse has clicked an object of the view.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     *
     * Method to check if the mouse has been pressed an object of the view.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     *
     * Method to check if the mouse has been released an object of the view.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     *
     * Method to check if the mouse has entered an object of the view.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mouseEntered(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "endBattleBtn":
                endBattleBtn.switchImage(SpritePath.END_BATTLE_BUTTON_HOVER);
                break;
        }
    }

    /**
     *
     * Method to check if the mouse has exited an object of the view.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mouseExited(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "endBattleBtn":
                endBattleBtn.switchImage(SpritePath.END_BATTLE_BUTTON);
                break;
        }
    }

    /**
     *
     * Method to add to the table the listeners.
     *
     * @param mouseListener listener that you want to add to the table cells.
     *
     */

    public void registerController(MouseListener mouseListener) {

        for (Cell[] cells : table) {
            for (Cell cell : cells) {
                cell.addMouseListener(mouseListener);
            }
        }

        endBattleBtn.addMouseListener(mouseListener);

    }

    /**
     *
     * Method to paint the right panel in the view.
     *
     * @param numberOfEnemies number of enemies against we play.
     *
     */

    public void paintLayout(int numberOfEnemies) {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 2;
        gbc.gridy = 0;
        backgound.add(rightPanel(numberOfEnemies), gbc);

        add(backgound);

    }

    /**
     *
     * Method to update the enemies panels.
     * Update the enemies tables and the enemies ships status.
     *
     * @param players arraylist of the enemies.
     *
     */

    public void updateGame(ArrayList<Player> players) {

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);

            Board board = p.getBoard();
            Ship[] ships = p.getShips();

            Tile[][] tiles = board.getTiles();

            if (p instanceof Human) {

                updateBoard(tiles);
                updateShips(ships);

            } else {
                JEnemy jEnemy = enemies.get(i - 1);
                jEnemy.updateEnemy(board, ships);
            }

        }

    }

    /**
     *
     * Method to update the ships in the table.
     *
     * @param ships list with all the ships pieces.
     *
     */

    private void updateShips(Ship[] ships) {
        for (int i = 0; i < ships.length; i++) {
            shipsStatus[i].updateStatus(ships[i].isSunk());
            shipsStatusTable.repaint();
        }
    }

    /**
     *
     * Method to update the table of the game.
     *
     * @param tiles the cell position in the table.
     *
     */

    private void updateBoard(Tile[][] tiles) {

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                TileType status = tiles[i][j].getTileType();
                if (status == TileType.SHIP) {
                    ShipSegment shipSegment = (ShipSegment) tiles[i][j];
                    replaceShipImage(shipSegment, i, j);
                } else if (status == TileType.HIT) {
                    table[i][j].switchImage(SpritePath.HIT);
                } else if (status == TileType.MISS) {
                    table[i][j].switchImage(SpritePath.MISS);
                }
            }
        }

    }

    /**
     *
     * Method to replace the water cell in the table with the ship image.
     *
     * @param shipSegment ship segment.
     * @param i x position of the table.
     * @param j y position of the table.
     *
     */

    private void replaceShipImage(ShipSegment shipSegment, int i, int j) {

        Ship ship = shipSegment.getShip();
        ShipSegment[] shipSegments = ship.getShipSegments();
        float scale = 0.2F;

        for (int piece = 0; piece < shipSegments.length; piece++) {
            ShipSegment segment = shipSegments[piece];
            if (segment.equals(shipSegment)) {
                if (ship instanceof Boat) {
                    table[i][j].switchImage(SpritePath.BOAT_PIECES, piece, scale, ship.getOrientation());
                } else if (ship instanceof Submarine) {
                    table[i][j].switchImage(SpritePath.SUBMARINE_PIECES, piece, scale, ship.getOrientation());
                } else if (ship instanceof Destroyer) {
                    table[i][j].switchImage(SpritePath.DESTRUCTOR_PIECES, piece, scale, ship.getOrientation());
                } else if (ship instanceof AircraftCarrier) {
                    table[i][j].switchImage(SpritePath.AIRCRAFT_PIECES, piece, scale, ship.getOrientation());
                }
            }
        }
    }

    /**
     *
     * Method to update the current time of the game.
     *
     * @param time String of the time.
     *
     */

    public void updateTime(String time) {
        gameTime.setText(time);
    }

    /**
     *
     * Method to update the attack status. If you can attack,
     * or you have to wait your turn.
     *
     * @param recharging String text to show in the status.
     *
     */

    public void updatePhase(String recharging) {
        statusAttack.setText(recharging);
    }

    public void reset() {

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j].switchImage(SpritePath.WATER);
            }
        }

    }

    public void returnMenu(String menu) {
        mainView.switchPanel(menu);
    }
}

