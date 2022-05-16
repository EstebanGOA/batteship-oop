package presentation.views;

import presentation.controllers.LoginController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class LoginView extends JPanel implements MouseListener {

    /**
     * Guardamos la referencia del parent para cambiar las ventanas al realizar ciertas interacciones en la ventana actual.
     */
    private final MainView mainView;

    private final JImagePanel jLoginButton;
    private final JTextField jEmailInput;
    private final JPasswordField jPasswordInput;
    private final JLabel jRegisterAccount;

    private final JImagePanel backgroundPanel;

    public LoginView(MainView mainView) {

        this.mainView = mainView;

        Font font = initializeFont();

        // ------------------------ Background Image ------------------------ //

        backgroundPanel = new JImagePanel("sprites/login_background_v2.png");
        backgroundPanel.setPreferredSize(new Dimension(1280, 720));
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setName("background_panel");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ------------------------ Login Button ------------------------ //

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(200,100));
        buttonPanel.setOpaque(false);

        jLoginButton = new JImagePanel("sprites/login_button.png");
        jLoginButton.setPreferredSize(new Dimension(100,100));
        jLoginButton.setOpaque(false);
        // TODO: Primero deberíamos hacer la petición al servidor, comprobar los datos y cuando todo sea correcto cambiar ventana.
        jLoginButton.setName("login");

        buttonPanel.add(jLoginButton);

        // ------------------------ Email Box Layout ------------------------ //

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.setPreferredSize(new Dimension(350, 35));
        emailPanel.setOpaque(false);

        JImagePanel emailIco = new JImagePanel("sprites/email_ico.png");
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

        // ------------------------ Password Box Layout ------------------------ //

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.setPreferredSize(new Dimension(350, 35));
        passwordPanel.setOpaque(false);

        JImagePanel passwordIco = new JImagePanel("sprites/lock_ico.png");
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

        // ----------------- Label to create a new account ----------------- //

        jRegisterAccount = new JLabel();
        jRegisterAccount.setText("Not registered? Create an account");
        jRegisterAccount.setFont(font);
        jRegisterAccount.setForeground(new Color(200,200,200));
        jRegisterAccount.setName("create_account");


        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(new JSeparator(0,150), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(emailPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(new JSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(passwordPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        backgroundPanel.add(new JSeparator(0,75), gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        backgroundPanel.add(buttonPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        backgroundPanel.add(new JSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        backgroundPanel.add(jRegisterAccount, gbc);

        this.add(backgroundPanel);
        //setVisible(true);

        initializeListeners();
    }

    public void initializeListeners () {
        jEmailInput.addMouseListener(this);
        jPasswordInput.addMouseListener(this);
        backgroundPanel.addMouseListener(this);
        jLoginButton.addMouseListener(this);
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

    public void registerView() {
        mainView.switchPanel("register");
    }

    public void menuView() {
        mainView.switchPanel("menu");
    }

    public void registerMasterView(MouseListener mouseListener) {
        jRegisterAccount.addMouseListener(mouseListener);
    }

    public void registerController(MouseListener mouseListener) {
        jLoginButton.addMouseListener(mouseListener);
    }

    public String getLogin() {
        return jEmailInput.getText();
    }

    public String getPassword() {
        return new String(jPasswordInput.getPassword());
    }

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

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "login":
                jLoginButton.switchImage("sprites/login_button_hover.png");
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String event = ((JComponent) e.getSource()).getName();

        switch (event) {
            case "login":
                jLoginButton.switchImage("sprites/login_button.png");
                break;
        }
    }
}
