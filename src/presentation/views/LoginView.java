package presentation.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Class LoginView that extends JPanel and implements a MouseListener.
 * This classed is used to display de login.
 */
public class LoginView extends JPanel implements MouseListener {

    /**
     * Guardamos la referencia del parent para cambiar las ventanas al realizar ciertas interacciones en la ventana actual.
     */
    private final MainView mainView;

    private JImagePanel jLoginButton;
    private JTextField jEmailInput;
    private JPasswordField jPasswordInput;
    private JLabel jRegisterAccount;

    private final JImagePanel backgroundPanel;

    private final Font font = initializeFont();

    /**
     *
     * Constructor of the LoginView class.
     *
     * @param mainView that controls the login window.
     *
     */

    public LoginView(MainView mainView) {
        this.mainView = mainView;

        // ------------------------ Background Image ------------------------ //

        backgroundPanel = setBackground();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(new JSeparator(0,150), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(emailPanel(), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(new JSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(passwordPanel(), gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        backgroundPanel.add(new JSeparator(0,75), gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        backgroundPanel.add(loginButton(), gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        backgroundPanel.add(new JSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        backgroundPanel.add(registerAccountLabel(), gbc);

        this.add(backgroundPanel);

        initializeListeners();
    }

    /**
     *
     * Method to create the login button.
     *
     * @return the login button.
     */

    public JPanel loginButton () {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(200,100));
        buttonPanel.setOpaque(false);

        jLoginButton = new JImagePanel(SpritePath.LOGIN_BUTTON);
        jLoginButton.setPreferredSize(new Dimension(100,100));
        jLoginButton.setOpaque(false);
        jLoginButton.setName("login");

        buttonPanel.add(jLoginButton);
        return buttonPanel;
    }

    /**
     *
     * Method to create the email input panel.
     *
     * @return th email input panel.
     */

    public JPanel emailPanel () {
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.setPreferredSize(new Dimension(350, 35));
        emailPanel.setOpaque(false);

        JImagePanel emailIco = new JImagePanel(SpritePath.EMAIL_ICON);
        emailIco.setOpaque(false);
        emailIco.setPreferredSize(new Dimension(35,0));

        jEmailInput = new JTextField(10);
        jEmailInput.setOpaque(false);
        jEmailInput.setFont(font);
        jEmailInput.setText("Username or Email");
        jEmailInput.setName("email_input");
        jEmailInput.setForeground(new Color(255,255,255, 125));
        jEmailInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        Border oldBorder = jEmailInput.getBorder();
        Border whiteBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder = BorderFactory.createCompoundBorder(whiteBorder, oldBorder);
        jEmailInput.setBorder(newBorder);

        emailPanel.add(emailIco);
        emailPanel.add(new JSeparator(15,0));
        emailPanel.add(jEmailInput);
        return emailPanel;
    }

    /**
     *
     * Method to create the password input panel.
     *
     * @return the password input panel.
     */

    public JPanel passwordPanel () {
        // ------------------------ Password Box Layout ------------------------ //

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.setPreferredSize(new Dimension(350, 35));
        passwordPanel.setOpaque(false);

        JImagePanel passwordIco = new JImagePanel(SpritePath.PASSWORD_ICON);
        passwordIco.setOpaque(false);
        passwordIco.setPreferredSize(new Dimension(35,0));

        jPasswordInput = new JPasswordField();
        jPasswordInput.setOpaque(false);
        jPasswordInput.setFont(font);
        jPasswordInput.setText("Password");
        jPasswordInput.setName("password_input");
        jPasswordInput.setEchoChar((char)0); // show the password
        jPasswordInput.setForeground(new Color(255,255,255, 125));
        jPasswordInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        Border oldBorder2 = jPasswordInput.getBorder();
        Border whiteBorder2 = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder2 = BorderFactory.createCompoundBorder(whiteBorder2, oldBorder2);
        jPasswordInput.setBorder(newBorder2);

        passwordPanel.add(passwordIco);
        passwordPanel.add(new JSeparator(15,0));
        passwordPanel.add(jPasswordInput);
        return passwordPanel;
    }

    /**
     *
     * Method to create the register account text.
     *
     * @return the register account text.
     */

    public JLabel registerAccountLabel () {
        jRegisterAccount = new JLabel();
        jRegisterAccount.setText("Not registered? Create an account");
        jRegisterAccount.setFont(font);
        jRegisterAccount.setForeground(new Color(200,200,200));
        jRegisterAccount.setName("create_account");
        return jRegisterAccount;
    }

    /**
     *
     * Method to set the background Image of the login window.
     *
     * @return the background image panel.
     *
     */

    public JImagePanel setBackground () {
        JImagePanel bg = new JImagePanel(SpritePath.LOGIN_BACKGROUND);
        bg.setPreferredSize(new Dimension(1280, 720));
        bg.setLayout(new GridBagLayout());
        bg.setName("background_panel");
        return bg;
    }

    /**
     *
     * Method to initialize all the listeners of the loginView window.
     *
     */

    public void initializeListeners () {
        jEmailInput.addMouseListener(this);
        jPasswordInput.addMouseListener(this);
        backgroundPanel.addMouseListener(this);
        jLoginButton.addMouseListener(this);
    }

    /**
     *
     * Method to initialize the font of the texts of the window.
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
     * Method to switch to the register view panel.
     *
     */

    public void registerView() {
        mainView.switchPanel("register");
    }

    /**
     *
     * Method to switch to the menu view panel.
     *
     */

    public void menuView(String username) {
        mainView.switchPanel("menu");
        mainView.setUsername(username);
    }

    /**
     *
     * Method to register add the listener to the register button.
     *
     * @param mouseListener the listener of the view.
     *
     */

    public void registerMasterView(MouseListener mouseListener) {
        jRegisterAccount.addMouseListener(mouseListener);
    }

    /**
     *
     * Method to add the listener to the login button.
     *
     * @param mouseListener the listener of the view.
     *
     */

    public void registerController(MouseListener mouseListener) {
        jLoginButton.addMouseListener(mouseListener);
    }

    /**
     *
     * Method to return the email input from the login view.
     *
     * @return the email input text.
     *
     */

    public String getLogin() {
        return jEmailInput.getText();
    }

    /**
     *
     * Method to get the password from the input.
     *
     * @return the password input.
     *
     */

    public String getPassword() {
        return new String(jPasswordInput.getPassword());
    }

    /**
     *
     * Method to manage the mouseClicked listeners of the view.
     *
     * @param e MouseEvent of the components of the view.
     *
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "email_input":
                jEmailInput.setText("");
                jEmailInput.setForeground(new Color(255,255,255));

                if (jPasswordInput.getPassword().length == 0) {
                    jPasswordInput.setText("Password");
                    jPasswordInput.setEchoChar((char)0);
                    jPasswordInput.setForeground(new Color(255,255,255, 125));
                }
                break;
            case "password_input":
                jPasswordInput.setText("");
                jPasswordInput.setEchoChar('*');
                jPasswordInput.setForeground(new Color(255,255,255));

                if (jEmailInput.getText().equals("")) {
                    jEmailInput.setText("Username or Email");
                    jEmailInput.setForeground(new Color(255, 255, 255, 125));
                }
                break;
            case "background_panel":
                if (jEmailInput.getText().equals("")) {
                    jEmailInput.setText("Username or Email");
                    jEmailInput.setForeground(new Color(255, 255, 255, 125));
                }
                if (jPasswordInput.getPassword().length == 0) {
                    jPasswordInput.setText("Password");
                    jPasswordInput.setEchoChar((char)0);
                    jPasswordInput.setForeground(new Color(255,255,255, 125));
                }
                break;
        }
    }

    /**
     *
     * Method to manage the mousePressed listener of the view.
     *
     * @param e MouseEvent of the component of the view.
     *
     */

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     *
     * Method to manage the mouseReleased listener of the view.
     *
     * @param e MouseEvent of the component of the view.
     *
     */

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     *
     * Method to manage the mouseEntered listener of the view.
     *
     * @param e MouseEvent of the component of the view.
     *
     */

    @Override
    public void mouseEntered(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "login":
                jLoginButton.switchImage(SpritePath.LOGIN_BUTTON_HOVER);
                break;
        }
    }

    /**
     *
     * Method to manage the mouseExited listener of the view.
     *
     * @param e MouseEvent of the component of the view.
     *
     */

    @Override
    public void mouseExited(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "login":
                jLoginButton.switchImage(SpritePath.LOGIN_BUTTON);
                break;
        }
    }

    public void reset() {
        jEmailInput.setText("Username or Email");
        jPasswordInput.setText("Password");
    }
}
