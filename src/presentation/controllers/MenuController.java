package presentation.controllers;

import business.GameManager;
import business.UserManager;
import business.entities.Player;
import presentation.views.GameStageView;
import presentation.views.JPopup;
import presentation.views.MenuView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MenuController implements MouseListener {

    private UserManager userManager;
    private GameManager gameManager;
    private MenuView menuView;
    private GameStageView gameStageView;

    public MenuController(UserManager userManager, GameManager gameManager, MenuView menuView, GameStageView gameStageView) {
        this.userManager = userManager;
        this.gameManager = gameManager;
        this.menuView = menuView;
        this.gameStageView = gameStageView;
    }

    private void getFilename() {
        String name = JOptionPane.showInputDialog("Enter the filename to load: ");
        if (name == null || name.isEmpty()) {
            new JPopup("You must enter a valid name!");
        } else {
            if (gameManager.fileExist(name)) {
                loadSavedGame(name);
            } else {
                new JPopup("There is no game with the specified name.");
            }
        }
    }

    private void loadSavedGame(String gameName) {

        int numberOfEnemies = gameManager.loadGame(gameName);
        gameStageView.paintLayout(numberOfEnemies);
        ArrayList<Player> players = gameManager.getPlayers();
        gameManager.startGame();
        gameStageView.updateGame(players);
        menuView.gameView();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "settings" -> menuView.settingsView();
            case "loadGame" -> getFilename();
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

