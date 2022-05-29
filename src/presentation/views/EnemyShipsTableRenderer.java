package presentation.views;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Class EnemyShipsTableRenderer implements TableCellRenderer.
 */
public class EnemyShipsTableRenderer implements TableCellRenderer {
    private EnemyText[] shipsNames;
    private EnemyText[] shipsStatus;

    /**
     *
     * Constructor of the table renderer.
     *
     * @param shipsNames list of the ships names
     * @param shipsStatus array of the enemies ships' status.
     *
     */

    public EnemyShipsTableRenderer (EnemyText[] shipsNames, EnemyText[] shipsStatus) {
        this.shipsNames = shipsNames;
        this.shipsStatus = shipsStatus;
    }

    /**
     *
     * Method to set the cells of the table.
     *
     * @param table the table.
     * @param value the object we want to insert.
     * @param isSelected check if the cell is selected.
     * @param hasFocus check if the cell is focused.
     * @param row the table row.
     * @param column the table column.
     *
     * @return the object we want to insert in the table.
     *
     */

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (column == 0)
            return shipsNames[row];
        else
            return shipsStatus[row];
    }
}
