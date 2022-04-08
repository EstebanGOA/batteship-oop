import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RegisterView extends JFrame {

    public RegisterView() {

        initializeWindow();

        Font font = initializeFont();

        // ------------------------ Background Image ------------------------ //

        JImagePanel backgroundPanel = new JImagePanel("sprites/login_background.png");
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ------------------------ Login Button ------------------------ //

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(200,100));
        buttonPanel.setOpaque(false);

        JImagePanel button = new JImagePanel("sprites/register_button.png");
        button.setPreferredSize(new Dimension(100,100));
        button.setOpaque(false);

        buttonPanel.add(button);

        // ------------------------ Email Box Layout ------------------------ //

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.setPreferredSize(new Dimension(350, 35));
        emailPanel.setOpaque(false);

        JImagePanel emailIco = new JImagePanel("sprites/email_ico.png");
        emailIco.setOpaque(false);
        emailIco.setPreferredSize(new Dimension(35,0));

        JTextField emailInput = new JTextField(10);
        emailInput.setOpaque(false);
        emailInput.setFont(font);
        emailInput.setForeground(new Color(255,255,255));
        emailInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        Border oldBorder = emailInput.getBorder();
        Border whiteBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder = BorderFactory.createCompoundBorder(whiteBorder, oldBorder);
        emailInput.setBorder(newBorder);

        emailPanel.add(emailIco);
        emailPanel.add(addSeparator(20,0));
        emailPanel.add(emailInput);

        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(addSeparator(0,125), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(emailPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(addSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(passwordPanel(font), gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        backgroundPanel.add(addSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        backgroundPanel.add(passwordPanel(font), gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        backgroundPanel.add(addSeparator(0,75), gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        backgroundPanel.add(buttonPanel, gbc);

        add(backgroundPanel);

        setVisible(true);
    }

    public JPanel passwordPanel (Font font) {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.setPreferredSize(new Dimension(350, 35));
        passwordPanel.setOpaque(false);

        JImagePanel passwordIco = new JImagePanel("sprites/lock_ico.png");
        passwordIco.setOpaque(false);
        passwordIco.setPreferredSize(new Dimension(35,0));

        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setOpaque(false);
        passwordInput.setFont(font);
        passwordInput.setForeground(new Color(255,255,255));
        passwordInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        Border oldBorder2 = passwordInput.getBorder();
        Border whiteBorder2 = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder2 = BorderFactory.createCompoundBorder(whiteBorder2, oldBorder2);
        passwordInput.setBorder(newBorder2);

        passwordPanel.add(passwordIco);
        passwordPanel.add(addSeparator(20,0));
        passwordPanel.add(passwordInput);

        return passwordPanel;
    }

    public void initializeWindow () {
        setSize(1280, 720);

        setTitle("Battleship v1.0.1");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Font initializeFont () {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    public JPanel addSeparator (int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box. createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }
}
