package presentation.views;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class EnemyShipsTableRenderer implements TableCellRenderer {
    private List<EnemyText> shipsNames;
    private EnemyText[] shipsStatus;

    public EnemyShipsTableRenderer (List<EnemyText> shipsNames, EnemyText[] shipsStatus) {
        this.shipsNames = shipsNames;
        this.shipsStatus = shipsStatus;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (column == 0)
            return shipsNames.get(row);
        else
            return shipsStatus[row];
    }
}
