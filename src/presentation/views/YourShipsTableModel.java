package presentation.views;

import javax.swing.table.DefaultTableModel;

public class YourShipsTableModel extends DefaultTableModel {
    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 0)
            return ShipPanel.class;
        else
            return JShipStatus.class;
    }

    @Override
    public int getRowCount() {
        return 5;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
}
