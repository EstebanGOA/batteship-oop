import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LogoutAndDeletionAccountView extends JFrame {

    // Images Paths

    private final String BACK_BUTTON_IMAGE    = "sprites/back_button.png";
    private final String DELETE_ACCOUNT_IMAGE = "sprites/delete_account_button.png";
    private final String LOGOUT_BUTTON        = "sprites/logout_button.png";

    // Background Color

    private final Color BACKGROUND_COLOR = new Color(39,152,213);

    // Font Path

    private final String FONT = "fonts/Poppins-Bold.ttf";

    public LogoutAndDeletionAccountView () {
        initializeWindow();

        Font font = initializeFont();

        // Top Panel include the space, the back button and the title of the window.

        JPanel topPanel = new JPanel();
            topPanel.setLayout(new BorderLayout());
            topPanel.setOpaque(false);

            // Box Layout to add space on the left of the back button

            JPanel spaceAndBackButton = new JPanel();
                spaceAndBackButton.setOpaque(false);
                spaceAndBackButton.setLayout(new BoxLayout(spaceAndBackButton, BoxLayout.X_AXIS));

                // Back Button Image

                JImagePanel backButton = new JImagePanel(BACK_BUTTON_IMAGE);
                    backButton.setPreferredSize(new Dimension(75, 0));
                    backButton.setOpaque(false);

            // Window Title

            JLabel jLabel = new JLabel();
                jLabel.setLayout(new BorderLayout());
                jLabel.setFont(font);
                jLabel.setOpaque(false);
                jLabel.setText("Settings");
                jLabel.setForeground(Color.white);
                jLabel.setBorder(BorderFactory.createEmptyBorder());
                jLabel.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));


        spaceAndBackButton.add(addSeparator(75,0));
        spaceAndBackButton.add(backButton);

        jLabel.add(spaceAndBackButton, BorderLayout.WEST);
        topPanel.add(jLabel, BorderLayout.CENTER);

        // ------------------------ Initializing Buttons ------------------------ //

        JImagePanel deleteAccountButton = new JImagePanel(DELETE_ACCOUNT_IMAGE);
            deleteAccountButton.setPreferredSize(new Dimension(250,75));
            deleteAccountButton.setOpaque(false);

        JImagePanel logoutButton = new JImagePanel(LOGOUT_BUTTON);
            logoutButton.setPreferredSize(new Dimension(250,75));
            logoutButton.setOpaque(false);

        // ------------------------ Background Image ------------------------ //

        JPanel backgroundPanel = new JPanel();
            backgroundPanel.setBackground(BACKGROUND_COLOR);
            backgroundPanel.setLayout(new BorderLayout());

        // ------------------------ Display Objects ------------------------ //

        JPanel grid = new JPanel();
            grid.setLayout(new GridBagLayout());
            grid.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            grid.add(deleteAccountButton, gbc);

            gbc.gridx = 1; gbc.gridy = 0;
            grid.add(addSeparator(25,0), gbc);

            gbc.gridx = 2; gbc.gridy = 0;
            grid.add(logoutButton, gbc);

        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(grid, BorderLayout.CENTER);

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
            font = Font.createFont(Font.TRUETYPE_FONT, new File(FONT)).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(FONT)));
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
