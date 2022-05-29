package presentation.controllers;

import business.UserManager;
import presentation.views.JPopup;
import presentation.views.SettingsView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * SettingsController class that implements a MouseListener.
 * The SettingsController takes care of the basic settings of the program.
 */
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
            case "delete" -> {
                settingsView.viewLogin();
                if (userManager.delete())
                    new JPopup("User account deleted successfully");
                else
                    new JPopup("Error, user cannot be deleted.");


            }
            case "back" -> {
                settingsView.viewMenu();
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
