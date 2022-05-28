package presentation.controllers;

import business.UserManager;
import presentation.views.JPopup;
import presentation.views.LoginView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginController implements MouseListener {
    private final LoginView loginView;
    private final UserManager userManager;

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
                    loginView.menuView();
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
