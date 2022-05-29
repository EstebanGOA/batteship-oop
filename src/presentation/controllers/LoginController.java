package presentation.controllers;

import business.UserManager;
import presentation.views.JPopup;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * LoginController class that implements a MouseListener.
 * The LoginController is responsible to communicate between the GameManager and the LoginView.
 */
public class LoginController implements MouseListener {
    private final LoginView loginView;
    private final UserManager userManager;


    /**
     * Constructor of GameController
     * @param loginView The LoginView that controls the view of the login.
     * @param userManager The UserManager that controls the logic of the user.
     */
    public LoginController(LoginView loginView, UserManager userManager) {
        this.loginView = loginView;
        this.userManager = userManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "login" -> {
                String login = loginView.getLogin();
                String password = loginView.getPassword();
                if (userManager.isLogin(login, password)) {
                    loginView.reset();
                    loginView.menuView(userManager.getUser().getName());
                } else {
                    new JPopup("Login or password incorrect");
                }

            }
            case "register" -> loginView.registerView();
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
