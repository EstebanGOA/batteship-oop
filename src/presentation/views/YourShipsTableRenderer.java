package presentation.views;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class YourShipsTableRenderer implements TableCellRenderer {
    private List<ShipPanel> ships;
    private List<JShipStatus> shipsStatus;

    public YourShipsTableRenderer (List<ShipPanel> ships, List<JShipStatus> shipsStatus) {
        this.ships = ships;
        this.shipsStatus = shipsStatus;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (column == 0)
            return ships.get(row);
        else
            return shipsStatus.get(row);
    }
}
