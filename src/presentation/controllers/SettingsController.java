package presentation.controllers;

import business.UserManager;
import presentation.views.JPopup;
import presentation.views.SettingsView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class SettingsController implements MouseListener {
    private UserManager userManager;
    private SettingsView settingsView;


    public SettingsController(UserManager userManager, SettingsView settingsView) {
        this.userManager = userManager;
        this.settingsView = settingsView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "logout" ->  {
                settingsView.viewLogin();
                userManager.logout();
                new JPopup("Logout successfully");

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
