import javax.swing.*;

public class JPopup {
    public JPopup (String msg) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, msg);
    }
}
