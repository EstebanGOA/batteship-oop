package presentation.views;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

/**
 * Class YourShipsTableRenderer that implements a TableCellRenderer.
 * This class is used to display your board and the status of your ships.
 */
public class YourShipsTableRenderer implements TableCellRenderer {
    private List<ShipPanel> ships;
    private JShipStatus[] shipsStatus;

    /**
     *
     * Constructor of the table renderer.
     *
     * @param ships list of the ships panels
     * @param shipsStatus array of the ships' status.
     *
     */

    public YourShipsTableRenderer (List<ShipPanel> ships, JShipStatus[] shipsStatus) {
        this.ships = ships;
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
            return ships.get(row);
        else
            return shipsStatus[row];
    }
}
