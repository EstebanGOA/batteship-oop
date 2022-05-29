package presentation.views;

import javax.swing.table.DefaultTableModel;

/**
 * Class YourShipsTableModel that extends DefaultTableModel.
 * Thi class is used to create the user table.
 */
public class YourShipsTableModel extends DefaultTableModel {

    /**
     *
     * Method to set the type of the class we are using for the custom table.
     *
     * @param columnIndex column index of the table.
     *
     * @return the class we want to use for the custom table.
     *
     */

    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 0)
            return ShipPanel.class;
        else
            return JShipStatus.class;
    }

    /**
     *
     * Method to get the number of rows the table has.
     *
     * @return the number of rows of the table.
     *
     */

    @Override
    public int getRowCount() {
        return 5;
    }

    /**
     *
     * Method to get the number of columns the table has.
     *
     * @return the number of columns.
     *
     */

    @Override
    public int getColumnCount() {
        return 2;
    }
}
