import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginView extends JFrame {
    public LoginView() {
        setSize(1280, 720);

        setTitle("Battleship v1.0.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // ------------------------ Background Image ------------------------ //

        JImagePanel backgroundPanel = new JImagePanel("sprites/login_background.png");
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ------------------------ Login Button ------------------------ //

        JImagePanel button = new JImagePanel("sprites/login_button.png");
        button.setPreferredSize(new Dimension(200,100));
        button.setOpaque(false);

        // ------------------------ Email EditText ------------------------ //

        JTextField emailInput = new JTextField(10);
        emailInput.setOpaque(false);
        emailInput.setFont(font);
        emailInput.setForeground(new Color(255,255,255));
        emailInput.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        Border oldBorder = emailInput.getBorder();
        Border whiteBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder = BorderFactory.createCompoundBorder(whiteBorder, oldBorder);
        emailInput.setBorder(newBorder);

        /*TextPrompt placeholder = new TextPrompt("Apellido Paterno", textField);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        JOptionPane.showMessageDialog(null, textField);*/

        //emailInput.setPreferredSize(new Dimension(350,50));

        // ------------------------ Password EditText ------------------------ //

        JTextField passwordInput = new JTextField();
        passwordInput.setOpaque(false);
        passwordInput.setFont(font);
        passwordInput.setForeground(new Color(255,255,255));
        passwordInput.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        Border oldBorder2 = passwordInput.getBorder();
        Border whiteBorder2 = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder2 = BorderFactory.createCompoundBorder(whiteBorder2, oldBorder2);
        passwordInput.setBorder(newBorder2);

        //passwordInput.setPreferredSize(new Dimension(350,50));

        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(emailInput, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(passwordInput, gbc);

        // Space between button and text field

        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box. createRigidArea(new Dimension(100, 100));
        space.add(rigidArea);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(space, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(button, gbc);

        add(backgroundPanel);

        setVisible(true);
    }
}
