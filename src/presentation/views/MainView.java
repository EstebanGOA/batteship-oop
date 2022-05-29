package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Main view that will take care of switching between windows depending on the user's interaction with the system.
 */
public class MainView extends JFrame implements MouseListener {

    private LoginView loginView;
    private RegisterView registerView;
    private MenuView menuView;
    private SettingsView settingsView;
    private StatisticsView statisticsView;
    private SetupStageView setupStageView;
    private GameStageView gameStageView;
    private JPanel rootPanel;
    private CardLayout cardLayout;

    /**
     * When we want to switch between windows we have to pass the value indicated by parameter.
     */
    private final String LOGIN_WINDOW = "login";
    private final String REGISTER_WINDOW = "register";
    private final String MENU_WINDOW = "menu";
    private final String SETUP_STAGE_WINDOW = "setup";
    private final String SETTINGS_WINDOW = "settings";
    private final String STATISTICS_WINDOW = "statistics";
    private final String GAME_STAGE_WINDOW = "game";

    /**
     * Constructor of the MainView
     */
    public MainView() {
        this.cardLayout = new CardLayout();
        this.rootPanel = new JPanel();
        initializeWindow();
    }

    /**
     *
     * Method to assign the views.
     *
     * @param loginView the loginView
     * @param registerView the registerView
     * @param menuView the menuView
     * @param settingsView the settingsView
     * @param setupStageView the setupView
     * @param gameStageView the gameView
     *
     */

    public void assignViews(LoginView loginView, RegisterView registerView, MenuView menuView, SettingsView settingsView, StatisticsView statisticsView, SetupStageView setupStageView, GameStageView gameStageView) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.menuView = menuView;
        this.settingsView = settingsView;
        this.statisticsView = statisticsView;
        this.setupStageView = setupStageView;
        this.gameStageView = gameStageView;
    }

    /**
     * It will take care of assigning the windows to the CardLayout and changing the view if necessary.
     */
    public void run() {

        /* This JPanel is the base of the content for the rest of the windows */
        rootPanel.setLayout(cardLayout);

        /* We assign all system windows to the CardLayout */
        rootPanel.add(loginView, LOGIN_WINDOW);
        rootPanel.add(registerView, REGISTER_WINDOW);
        rootPanel.add(menuView, MENU_WINDOW);
        rootPanel.add(setupStageView, SETUP_STAGE_WINDOW);
        rootPanel.add(settingsView, SETTINGS_WINDOW);
        rootPanel.add(gameStageView, GAME_STAGE_WINDOW);
        rootPanel.add(statisticsView, STATISTICS_WINDOW);

        this.add(rootPanel);
        pack();
        this.setVisible(true);
    }

    public void switchPanel(String windowName) {
        cardLayout.show(rootPanel, windowName);

    }

    /**
     * Function that adds all the users in the system to the ComboBox.
     * @param users An array of the name of the users.
     */
    public void setStatisticsData(ArrayList<String> users){
            for(String user : users){
                statisticsView.addItem(user);
            }

    }

    /**
     * Function that sets the name of the user that has logged in, in the menu view.
     * @param username A string with the name of the user.
     */
    public void setUsername(String username) {
        menuView.setUsername(username);
    }

    /**
     * It will initialize the window with default values.
     */
    private void initializeWindow () {
        setSize(1280, 720);
        setTitle("Battleship v1.0.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JComponent) {
            /* For each event associated to a component we assign a name, depending on its value we will change the window. */
            switch (((JComponent) e.getSource()).getName()) {
                case "login" -> switchPanel(MENU_WINDOW);
                case "create_account" -> switchPanel(REGISTER_WINDOW);
                case "register", "return_login" -> switchPanel(LOGIN_WINDOW);
                case "start_game" -> switchPanel(GAME_STAGE_WINDOW);
                case "new_game" -> switchPanel(SETUP_STAGE_WINDOW);
                case "settings" -> switchPanel(SETTINGS_WINDOW);
            }
        }
    }

    /**
     *
     * Method to check if the mouse is pressed on a view object.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     *
     * Method to check if the mouse is released on a view object.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     *
     * Method to check if the mouse is entered on a view object.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     *
     * Method to check if the mouse is exited on a view object.
     *
     * @param e MouseEvent of an object of the view.
     *
     */

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
