package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * Class SettingsView that extends Jpanel.
 * This class is used to display the settings.
 */
public class SettingsView extends JPanel {

    private MainView mainView;

    // Background Color

    private final Color BACKGROUND_COLOR = new Color(39, 152, 213);

    // Font Path

    private final String FONT = "fonts/Poppins-Bold.ttf";
    private final Font font = initializeFont();

    private JImagePanel logoutButton;
    private JImagePanel deleteAccountButton;
    private JImagePanel backButton;

    /**
     * Constructor of the settings view where we create all the view.
     *
     * @param mainView which controls the current view.
     */
    public SettingsView(MainView mainView) {

        this.mainView = mainView;

        initializeWindow();

        // ------------------------ Initializing Buttons ------------------------ //

        deleteAccountButton = new JImagePanel(SpritePath.DELETE_ACCOUNT_BUTTON);
        deleteAccountButton.setPreferredSize(new Dimension(250, 75));
        deleteAccountButton.setOpaque(false);
        deleteAccountButton.setName("delete");

        logoutButton = new JImagePanel(SpritePath.LOGOUT_BUTTON);
        logoutButton.setPreferredSize(new Dimension(250, 75));
        logoutButton.setOpaque(false);
        logoutButton.setName("logout");

        // ------------------------ Background Image ------------------------ //

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(BACKGROUND_COLOR);
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setPreferredSize(new Dimension(1280, 720));

        // ------------------------ Display Objects ------------------------ //

        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        grid.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        grid.add(deleteAccountButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        grid.add(addSeparator(25, 0), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        grid.add(logoutButton, gbc);

        backgroundPanel.add(setTopPanel(), BorderLayout.NORTH);
        backgroundPanel.add(grid, BorderLayout.CENTER);

        add(backgroundPanel);

        setVisible(true);
    }

    /**
     * Method to set the top panel:
     * - The back button.
     * - The title text of the window.
     * - Some separators.
     *
     * @return the top panel.
     */

    public JPanel setTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);

        // Box Layout to add space on the left of the back button

        JPanel spaceAndBackButton = new JPanel();
        spaceAndBackButton.setOpaque(false);
        spaceAndBackButton.setLayout(new BoxLayout(spaceAndBackButton, BoxLayout.X_AXIS));

        // Back Button Image

        backButton = new JImagePanel(SpritePath.BACK_BUTTON);
        backButton.setPreferredSize(new Dimension(75, 0));
        backButton.setOpaque(false);
        backButton.setName("back");

        // Window Title

        JLabel jLabel = new JLabel();
        jLabel.setLayout(new BorderLayout());
        jLabel.setFont(font);
        jLabel.setOpaque(false);
        jLabel.setText("Settings");
        jLabel.setForeground(Color.white);
        jLabel.setBorder(BorderFactory.createEmptyBorder());
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));


        spaceAndBackButton.add(addSeparator(75, 0));
        spaceAndBackButton.add(backButton);

        jLabel.add(spaceAndBackButton, BorderLayout.WEST);
        topPanel.add(jLabel, BorderLayout.CENTER);
        return topPanel;
    }

    /**
     * Method to initialize the window of the settings view.
     */

    public void initializeWindow() {
        setSize(1280, 720);
    }

    /**
     * Method to initialize the font of the settings view.
     *
     * @return the initialized font.
     */

    public Font initializeFont() {

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(FONT)).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(FONT)));
            return font;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to create a separator between two panels.
     *
     * @param width  width of the separator panel.
     * @param height height of the separator panel.
     * @return the separator panel.
     */

    public JPanel addSeparator(int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box.createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }

    /**
     * Method to switch to the login panel.
     */

    public void viewLogin() {
        mainView.switchPanel("login");
    }

    /**
     * Method that switch to the manu panel.
     */

    public void viewMenu() {
        mainView.switchPanel("menu");
    }

    /**
     * Method to add the mouse listener to the logout button.
     *
     * @param mouseListener the mouse listener that we want to add to the logout button.
     */

    public void settingsController(MouseListener mouseListener) {
        logoutButton.addMouseListener(mouseListener);
        deleteAccountButton.addMouseListener(mouseListener);
        backButton.addMouseListener(mouseListener);
    }
}
