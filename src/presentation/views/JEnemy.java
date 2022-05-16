package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JEnemy extends JPanel {
    private final String PANEL_BG = "sprites/enemyBg.png";
    private final JPanel enemyShipsStatusAndTablePanel;

    private Cell[][] table = new Cell[15][15];

    private final String SPRITE_WATER = "sprites/GameViews/water.png";

    public JEnemy () {
        enemyShipsStatusAndTablePanel = new JPanel();
            enemyShipsStatusAndTablePanel.setOpaque(false);
            enemyShipsStatusAndTablePanel.setLayout(new GridBagLayout());

        JImagePanel enemyPanel = new JImagePanel(PANEL_BG);
            enemyPanel.setPreferredSize(new Dimension(290, 170));
            enemyPanel.setLayout(new GridBagLayout());
            enemyPanel.setOpaque(false);

        ArrayList<EnemyText> shipsNames = new ArrayList<>();
            shipsNames.add(new EnemyText("Boat"));
            shipsNames.add(new EnemyText("Submarine 1"));
            shipsNames.add(new EnemyText("Submarine 2"));
            shipsNames.add(new EnemyText("Destructor"));
            shipsNames.add(new EnemyText("Aircraft"));

        ArrayList<EnemyText> shipsStatus = new ArrayList<>();
            shipsStatus.add(new EnemyText("Intact"));
            shipsStatus.add(new EnemyText("Intact"));
            shipsStatus.add(new EnemyText("Intact"));
            shipsStatus.add(new EnemyText("Intact"));
            shipsStatus.add(new EnemyText("Intact"));

        // Insert the arraylist in the JTable
        JTable shipsStatusTable = new JTable (new EnemyShipsTableModel());
            shipsStatusTable.setDefaultRenderer(EnemyText.class, new EnemyShipsTableRenderer(shipsNames, shipsStatus));
            shipsStatusTable.setRowHeight(20);
            shipsStatusTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            shipsStatusTable.getColumnModel().getColumn(1).setPreferredWidth(60);
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
            enemyPanel.add(new JSeparator(0,2), gbc_yourShips);

        JPanel tableGrid = new JPanel();
            tableGrid.setLayout(new GridLayout(15, 15));
            tableGrid.setPreferredSize(new Dimension(150, 150));

            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    table[i][j] = new Cell(j, i, SPRITE_WATER);
                    tableGrid.add(table[i][j]);
                }
            }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        enemyShipsStatusAndTablePanel.add(enemyPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        enemyShipsStatusAndTablePanel.add(new JSeparator(0,2), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        enemyShipsStatusAndTablePanel.add(tableGrid, gbc);

        setBorder(BorderFactory.createEmptyBorder(-15, 0, -15, 0));
        setOpaque(false);
        add(enemyShipsStatusAndTablePanel);
    }
}
