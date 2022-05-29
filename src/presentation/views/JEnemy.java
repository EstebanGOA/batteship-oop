package presentation.views;

import business.entities.Board;
import business.entities.Ship;
import business.entities.Tile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Class JEnemy that extends a JPanel and displays the enemy board and status.
 */
public class JEnemy extends JPanel {

    private final JPanel enemyShipsStatusAndTablePanel;

    private Cell[][] table = new Cell[15][15];
    private EnemyText[] shipsStatus = new EnemyText[5];
    private EnemyText[] shipsName = new EnemyText[5];
    private JTable shipsStatusTable;

    /**
     *
     * Constructor of the enemy class that has:
     *      - Board enemy.
     *      - ShipStatus enemy.
     *
     */

    public JEnemy() {

        enemyShipsStatusAndTablePanel = new JPanel();
        enemyShipsStatusAndTablePanel.setOpaque(false);
        enemyShipsStatusAndTablePanel.setLayout(new GridBagLayout());

        JImagePanel enemyPanel = new JImagePanel(SpritePath.ENEMY_BACKGROUND);
        enemyPanel.setPreferredSize(new Dimension(290, 170));
        enemyPanel.setLayout(new GridBagLayout());
        enemyPanel.setOpaque(false);

        shipsName[0] = (new EnemyText("Boat"));
        shipsName[1] = (new EnemyText("Submarine 1"));
        shipsName[2] = (new EnemyText("Submarine 2"));
        shipsName[3] = (new EnemyText("Destructor"));
        shipsName[4] = (new EnemyText("Aircraft"));

        shipsStatus[0] = new EnemyText("Intact");
        shipsStatus[1] = new EnemyText("Intact");
        shipsStatus[2] = new EnemyText("Intact");
        shipsStatus[3] = new EnemyText("Intact");
        shipsStatus[4] = new EnemyText("Intact");

        // Insert the arraylist in the JTable
        shipsStatusTable = new JTable(new EnemyShipsTableModel());
        shipsStatusTable.setDefaultRenderer(EnemyText.class, new EnemyShipsTableRenderer(shipsName, shipsStatus));
        shipsStatusTable.setRowHeight(20);
        shipsStatusTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        shipsStatusTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        shipsStatusTable.setOpaque(false);
        shipsStatusTable.setEnabled(false);
        shipsStatusTable.setShowGrid(false);
        shipsStatusTable.setShowHorizontalLines(false);
        shipsStatusTable.setShowVerticalLines(false);

        GridBagConstraints gbc_yourShips = new GridBagConstraints();
        gbc_yourShips.gridx = 0;
        gbc_yourShips.gridy = 0;
        enemyPanel.add(shipsStatusTable, gbc_yourShips);

        gbc_yourShips.gridx = 0;
        gbc_yourShips.gridy = 1;
        enemyPanel.add(new JSeparator(0, 2), gbc_yourShips);

        JPanel tableGrid = new JPanel();
        tableGrid.setLayout(new GridLayout(15, 15));
        tableGrid.setPreferredSize(new Dimension(150, 150));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                table[i][j] = new Cell(j, i);
                table[i][j].setBackground(Color.BLUE);
                tableGrid.add(table[i][j]);
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                table[i][j].setBorder(border);

            }
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        enemyShipsStatusAndTablePanel.add(enemyPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        enemyShipsStatusAndTablePanel.add(new JSeparator(0, 2), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        enemyShipsStatusAndTablePanel.add(tableGrid, gbc);

        setBorder(BorderFactory.createEmptyBorder(-15, 0, -15, 0));
        setOpaque(false);
        add(enemyShipsStatusAndTablePanel);
    }

    /**
     *
     * Method to update the enemy
     *
     * @param board enemy board.
     * @param ships enemy ship.
     *
     */

    public void updateEnemy(Board board, Ship[] ships) {
        updateBoard(board);
        updateShips(ships);
    }

    /**
     *
     * Method to update the board of the current enemy.
     *
     * @param board Board with all the cells of the enemy.
     *
     */

    public void updateBoard(Board board) {

        Tile[][] tiles = board.getTiles();

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {

                Tile tile = tiles[i][j];
                table[i][j].setBackground(tile.getColor());

            }
        }

    }

    /**
     *
     * Method to update the ships of the enemy.
     *
     * @param ships pieces that you want to update.
     *
     */

    private void updateShips(Ship[] ships) {
        for (int i = 0; i < ships.length; i++) {
            shipsStatus[i].updateStatus(ships[i].isSunk());
            shipsStatusTable.repaint();
        }
    }
}
