package presentation.controllers;

import business.UserManager;
import business.entities.User;
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
                userManager.logoutUser();
                new JPopup("Logout successfully");

                break;
            }

            case "delete" -> {
                settingsView.viewLogin();
                userManager.deleteUser();
                new JPopup("User account deleted successfully");

                break;
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
