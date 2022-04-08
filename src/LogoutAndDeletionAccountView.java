import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LogoutAndDeletionAccountView extends JFrame {
    public LogoutAndDeletionAccountView () {
        initializeWindow();

        Font font = initializeFont();

        JLabel jLabel = new JLabel();
        jLabel.setText("Settings");
        jLabel.setFont(font);
        jLabel.setForeground(Color.white);
        jLabel.setOpaque(false);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JImagePanel deleteAccountButton = new JImagePanel("sprites/deleteaccount_button.png");
        deleteAccountButton.setPreferredSize(new Dimension(250,75));
        deleteAccountButton.setOpaque(false);

        JImagePanel logoutButton = new JImagePanel("sprites/logout_button.png");
        logoutButton.setPreferredSize(new Dimension(250,75));
        logoutButton.setOpaque(false);

        // ------------------------ Background Image ------------------------ //

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(39,152,213));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;

        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(jLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(addSeparator(0,200), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(deleteAccountButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(addSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        backgroundPanel.add(logoutButton, gbc);

        add(backgroundPanel);

        setVisible(true);
    }

    public void initializeWindow () {
        setSize(1280, 720);

        setTitle("Battleship v1.0.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Font initializeFont () {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")).deriveFont(30f);
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
