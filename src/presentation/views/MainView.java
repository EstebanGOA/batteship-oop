package presentation.views;

import business.entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Vista principal que se encargará de cambiar entre ventanas dependiendo de la interacción del usuario con el sistema.
 */
public class MainView extends JFrame implements MouseListener {

    private LoginView loginView;
    private RegisterView registerView;
    private MenuView menuView;
    private SettingsView settingsView;
    private StatisticsView statisticsView;
    private JPanel rootPanel;
    private CardLayout cardLayout;

    /**
     * Cuando queramos cambiar entre ventanas tenemos que pasar el valor indicado por parámetro.
     */
    private final String LOGIN_WINDOW = "login";
    private final String REGISTER_WINDOW = "register";
    private final String MENU_WINDOW = "menu";
    private final String SETTINGS_WINDOW = "settings";
    private final String STATISTICS_WINDOW = "statistics";


    /**
     * Constructor de MainView.
     */
    public MainView() {
        this.cardLayout = new CardLayout();
        this.rootPanel = new JPanel();
        initializeWindow();
    }

    public void asigneViews(LoginView loginView, RegisterView registerView, MenuView menuView, SettingsView settingsView, StatisticsView statisticsView) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.menuView = menuView;
        this.settingsView = settingsView;
        this.statisticsView = statisticsView;

    }

    /**
     * Se encargará de asignar las ventanas al CardLayout y cambiar la vista en caso de ser necesario.
     */
    public void run() {

        /* Este JPanel es la base del contenido para el resto de ventanas */
        rootPanel.setLayout(cardLayout);

        /* Asignamos todas las ventanas del sistema al CardLayout */
        rootPanel.add(loginView, LOGIN_WINDOW);
        rootPanel.add(registerView, REGISTER_WINDOW);
        rootPanel.add(menuView, MENU_WINDOW);
        rootPanel.add(settingsView, SETTINGS_WINDOW);
        rootPanel.add(statisticsView, STATISTICS_WINDOW);

        this.add(rootPanel);
        this.setVisible(true);
    }

    public void switchPanel(String windowName) {
        cardLayout.show(rootPanel, windowName);

    }


    public void setStatisticsData(ArrayList<String> users){
            for(String user : users){
                statisticsView.addItem(user);
            }

            statisticsView.addBars( 100);

            statisticsView.addBars( 8);

            statisticsView.addBars( 54);

            statisticsView.addBars( 23);


    }

    public void setUsername(String username) {
        menuView.setUsername(username);
    }

    /**
     * Inicializará con valores por defecto la ventana.
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
            /*  Para cada evento asociado a una componente asignamos un nombre, dependiendo de su valor cambiaremos la ventana. */
            switch (((JComponent) e.getSource()).getName()) {


            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
