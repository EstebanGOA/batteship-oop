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
import java.io.File;
import java.lang.reflect.Array;
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
        File[] files = gameManager.getFiles();

        if (files.length <= 0) {
            new JPopup("There are no saved games!");
        } else {
            ArrayList<String> filenames = new ArrayList<>();
            for (File file : files) {
                filenames.add(file.getName().split("\\.")[0]);
            }
            String game = (String) JOptionPane.showInputDialog(null, "Which game do you want to load?", "Select a game", JOptionPane.QUESTION_MESSAGE, null, filenames.toArray(), filenames.get(0));
            loadSavedGame(game);
        }
    }

    private void loadSavedGame(String gameName) {

        gameStageView.reset();
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

            case "statistics" -> menuView.statisticsView(userManager.getUsersName());
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

