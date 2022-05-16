package presentation.views;

import javax.swing.table.DefaultTableModel;

public class EnemyShipsTableModel extends DefaultTableModel {
    public Class<?> getColumnClass(int columnIndex) {
        return EnemyText.class;
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
