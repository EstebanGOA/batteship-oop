package presentation.views;

import javax.swing.*;

/**
 * Class JPopup.
 * This class is used to create popups.
 */
public class JPopup {

    /**
     *
     * Method to create popup message for the dialog and error messages.
     *
     * @param msg String message with the text you want to show in the dialog.
     *
     */

    public JPopup (String msg) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, msg);
    }
}
