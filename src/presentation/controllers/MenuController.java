package presentation.controllers;

import business.UserManager;
import presentation.views.MenuView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener {

    private UserManager userManager;
    private MenuView menuView;

    public MenuController(UserManager userManager, MenuView menuView) {
        this.userManager = userManager;
        this.menuView = menuView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "settings" -> menuView.settingsView();

            case "statistics" -> menuView.statisticsView(userManager.getUsersName());
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

    public void showUsername() {
        menuView.setUsername(userManager.getUser().getName());
    }

}

