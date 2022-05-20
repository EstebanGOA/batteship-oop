package presentation.views;

import presentation.controllers.LoginController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class LoginView extends JPanel {

    /**
     * Guardamos la referencia del parent para cambiar las ventanas al realizar ciertas interacciones en la ventana actual.
     */
    private final MainView mainView;

    private final JImagePanel jLoginButton;
    private final JTextField jEmailInput;
    private final JPasswordField jPasswordInput;
    private final JLabel jRegisterAccount;

    public LoginView(MainView mainView) {

        this.mainView = mainView;

        Font font = initializeFont();

        // ------------------------ Background Image ------------------------ //

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setPreferredSize(new Dimension(1280, 720));
        backgroundPanel.setLayout(new GridBagLayout());

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
        jEmailInput.setForeground(new Color(255,255,255));
        jEmailInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        Border oldBorder = jEmailInput.getBorder();
        Border whiteBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder = BorderFactory.createCompoundBorder(whiteBorder, oldBorder);
        jEmailInput.setBorder(newBorder);

        emailPanel.add(emailIco);
        emailPanel.add(addSeparator(20,0));
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
        jPasswordInput.setForeground(new Color(255,255,255));
        jPasswordInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        Border oldBorder2 = jPasswordInput.getBorder();
        Border whiteBorder2 = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        Border newBorder2 = BorderFactory.createCompoundBorder(whiteBorder2, oldBorder2);
        jPasswordInput.setBorder(newBorder2);

        passwordPanel.add(passwordIco);
        passwordPanel.add(addSeparator(20,0));
        passwordPanel.add(jPasswordInput);

        // ----------------- Label to create a new account ----------------- //

        jRegisterAccount = new JLabel();
        jRegisterAccount.setText("Not registered? Create an account");
        jRegisterAccount.setFont(font);
        jRegisterAccount.setForeground(new Color(200,200,200));
        jRegisterAccount.setName("create_account");


        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(addSeparator(0,150), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(emailPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(addSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(passwordPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        backgroundPanel.add(addSeparator(0,75), gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        backgroundPanel.add(buttonPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        backgroundPanel.add(addSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        backgroundPanel.add(jRegisterAccount, gbc);

        this.add(backgroundPanel);
        //setVisible(true);
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

    public void registerView() {
        mainView.switchPanel("register");
    }

    public void menuView(String username) {
        mainView.switchPanel("menu");
        mainView.setUsername(username);
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

}
