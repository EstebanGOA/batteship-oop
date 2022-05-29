package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class MenuView that extends JPanel.
 * This class is used to display the menu.
 */
public class MenuView extends JPanel {

    private final MainView mainView;

    private String username = "";

    private JImagePanel settingsButton;
    private JImagePanel loadGameButton;

    private final Font font = initializeFont();
    private JImagePanel statisticsButton;

    private JLabel usernameLabel;

    /**
     *
     * Constructor of the menuView.
     *
     * @param mainView which controls the menuView.
     *
     */

    public MenuView(MainView mainView) {

        this.mainView = mainView;

        // ------------------------ Background Image ------------------------ //
        // ---------- Grid layout to divide the window in 3 columns --------- //

        JImagePanel backgroundPanel = new JImagePanel(SpritePath.MENU_BACKGROUND);
            backgroundPanel.setPreferredSize(new Dimension(1280, 720));
            backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(createLeftColumn(), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        backgroundPanel.add(createMidColumn(), gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        backgroundPanel.add(createRightColumn(), gbc);

        add(backgroundPanel);
    }

    // --------------------------- Left Column -------------------------- //

    /**
     * Method to create the left column of the menu view.
     *
     * @return the left column of the menu view.
     *
     */

    public JPanel createLeftColumn () {
        JPanel leftColumn = new JPanel (new GridBagLayout());
        leftColumn.setOpaque(false);

        System.out.println(username);
        GridBagConstraints gbcLeftColumn = new GridBagConstraints();

        JPanel nameBackgroundPanel = new JImagePanel(SpritePath.TITLE_MENU);
        nameBackgroundPanel.setOpaque(false);
        nameBackgroundPanel.setLayout(new BorderLayout());
        nameBackgroundPanel.setPreferredSize(new Dimension(350,75));

        usernameLabel = new JLabel(username);
        usernameLabel.setFont(font);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        nameBackgroundPanel.add(usernameLabel);

        gbcLeftColumn.gridx = 0; gbcLeftColumn.gridy = 0;
        gbcLeftColumn.anchor = GridBagConstraints.FIRST_LINE_START;
        leftColumn.add(nameBackgroundPanel, gbcLeftColumn);

        gbcLeftColumn.gridx = 1; gbcLeftColumn.gridy = 0;
        leftColumn.add(addSeparator(100,0), gbcLeftColumn);

        gbcLeftColumn.gridx = 0; gbcLeftColumn.gridy = 1;
        leftColumn.add(addSeparator(0,570), gbcLeftColumn);

        return leftColumn;
    }

    // --------------------------- Mid Column -------------------------- //

    /**
     *
     * Method to create the mid-column of the menu view.
     *
     * @return the mid-column for the menu view.
     *
     */

    public JPanel createMidColumn () {
        JPanel midColumn = new JPanel (new GridBagLayout());
        midColumn.setOpaque(false);

        GridBagConstraints gbcMidColumn = new GridBagConstraints();

        JImagePanel newBattleButton = new JImagePanel(SpritePath.NEW_BATTLE_BUTTON);
        newBattleButton.setPreferredSize(new Dimension(350,125));
        newBattleButton.setOpaque(false);
        newBattleButton.setName("new_game");
        newBattleButton.addMouseListener(mainView);

        loadGameButton = new JImagePanel(SpritePath.LOAD_BATTLE_BUTTON);
        loadGameButton.setPreferredSize(new Dimension(350,125));
        loadGameButton.setOpaque(false);
        loadGameButton.setName("loadGame");

        gbcMidColumn.gridx = 0; gbcMidColumn.gridy = 0;
        midColumn.add(newBattleButton, gbcMidColumn);

        gbcMidColumn.gridx = 0; gbcMidColumn.gridy = 1;
        midColumn.add(addSeparator(0,50), gbcMidColumn);

        gbcMidColumn.gridx = 0; gbcMidColumn.gridy = 2;
        midColumn.add(loadGameButton, gbcMidColumn);

        return midColumn;
    }

    // --------------------------- Right Column -------------------------- //

    /**
     *
     * Method to create the right column of the menu view.
     *
     * @return the right column for the menu view.
     *
     */

    public JPanel createRightColumn () {
        JPanel rightColumn = new JPanel (new GridBagLayout());
        rightColumn.setOpaque(false);

        GridBagConstraints gbcRightColumn = new GridBagConstraints();

        // Settings Button
        settingsButton = new JImagePanel(SpritePath.SETTINGS_BUTTON);
        settingsButton.setPreferredSize(new Dimension(75,75));
        settingsButton.setOpaque(false);
        settingsButton.setName("settings");

        // Statistics Button
        statisticsButton = new JImagePanel(SpritePath.STATISTICS_BUTTON);
        statisticsButton.setPreferredSize(new Dimension(75,75));
        statisticsButton.setOpaque(false);
        statisticsButton.setName("statistics");

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 0;
        rightColumn.add(addSeparator(200,0), gbcRightColumn);

        gbcRightColumn.gridx = 1; gbcRightColumn.gridy = 0;
        gbcRightColumn.anchor = GridBagConstraints.FIRST_LINE_START;
        rightColumn.add(createTrophiesPanel(), gbcRightColumn);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 1;
        rightColumn.add(addSeparator(0,15), gbcRightColumn);

        gbcRightColumn.gridx = 1; gbcRightColumn.gridy = 2;
        gbcRightColumn.anchor = GridBagConstraints.FIRST_LINE_END;
        rightColumn.add(settingsButton, gbcRightColumn);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 3;
        rightColumn.add(addSeparator(0,15), gbcRightColumn);

        gbcRightColumn.gridx = 1; gbcRightColumn.gridy = 4;
        gbcRightColumn.anchor = GridBagConstraints.FIRST_LINE_END;
        rightColumn.add(statisticsButton, gbcRightColumn);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 5;
        rightColumn.add(addSeparator(0, 375), gbcRightColumn);

        return rightColumn;
    }

    /**
     *
     * Method to create the trophies' panel in the menu view.
     *
     * @return the trophies' panel.
     *
     */

    public JPanel createTrophiesPanel () {
        JImagePanel trophiesBackground = new JImagePanel(SpritePath.TROPHIES_BACKGROUND);
        trophiesBackground.setOpaque(false);
        trophiesBackground.setLayout(new BorderLayout());
        trophiesBackground.setPreferredSize(new Dimension(200, 75));

        JLabel trophiesLabel = new JLabel("0");
        trophiesLabel.setFont(font);
        trophiesLabel.setForeground(Color.white);
        trophiesLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        trophiesBackground.add(trophiesLabel);
        return trophiesBackground;
    }

    /**
     *
     * Method to initialize the font for the texts.
     *
     * @return the initialized font.
     *
     */

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

    /**
     *
     * Method to add a separator panel between two panels of the view.
     *
     * @param width of the separator panel.
     * @param height of the separator panel.
     *
     * @return the separator panel.
     *
     */

    public JPanel addSeparator (int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box. createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }

    /**
     *
     * Method to assign the listener to the settings button.
     *
     * @param mouseListener the mouse listener of the view.
     *
     */

    public void menuController(MouseListener mouseListener) {
        settingsButton.addMouseListener(mouseListener);
        loadGameButton.addMouseListener(mouseListener);
        statisticsButton.addMouseListener(mouseListener);
    }

    /**
     * Method to switch the panel of the settings view.
     */

    public void settingsView() {
        mainView.switchPanel("settings");
    }

    /**
     * Method that will switch to the statistics view and load the users' data in it.
     * @param users Arraylist with the username of all users.
     */

    public void statisticsView(ArrayList<String> users) {
        mainView.switchPanel("statistics");
        mainView.setStatisticsData(users);
    }

    /**
     *
     * Method to set the username of the player.
     *
     * @param username username of the player.
     *
     */

    public void setUsername(String username) {
        this.usernameLabel.setText("Username: " + username);
    }

    /**
     *
     * Method to switch to the game view.
     *
     */

    public void gameView() {
        mainView.switchPanel("game");
    }

}
