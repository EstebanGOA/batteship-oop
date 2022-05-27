package presentation.controllers;

import business.GameManager;
import business.entities.Human;
import business.entities.Player;
import presentation.views.Cell;
import presentation.views.GameStageView;
import presentation.views.JPopup;
import presentation.views.JSaveGame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameController implements MouseListener {

    private GameStageView gameStageView;
    private GameManager gameManager;

    public GameController(GameStageView gameStageView, GameManager gameManager) {
        this.gameStageView = gameStageView;
        this.gameManager = gameManager;
    }

    public void updateTimer(String time) {
        SwingUtilities.invokeLater(()->this.gameStageView.updateTime(time));
    }

    public void updateGame(ArrayList<Player> players) {
        SwingUtilities.invokeLater(()->this.gameStageView.updateGame(players));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "cell" -> {
                Cell cell = (Cell) e.getSource();
                int x = cell.getCoordinates()[0];
                int y = cell.getCoordinates()[1];
                Player player = gameManager.getPlayers().get(0);
                if (!player.isRecharging()) {
                   gameManager.attack(player, x, y);
                   gameStageView.updateGame(gameManager.getPlayers());
                }
            }
            case "endBattleBtn" -> {
                gameManager.stopGame();
                String gameName = JOptionPane.showInputDialog("Enter a name to save the game: ");
                if (gameName == null || gameName.isEmpty()) {
                    new JPopup("The game has not been stopped, it is necessary to enter a name");
                    gameManager.startGame();
                } else {
                    gameManager.saveUnfinishedGame(gameName);
                    new JPopup("The game has been saved successfully!");
                    gameStageView.returnMenu();
                    gameStageView.reset();
                }
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

    public void updatePhase(String recharging) {
        gameStageView.updatePhase(recharging);
    }

    public void returnMenu(Player player) {
        boolean isWinner = player instanceof Human;

        if (isWinner)
            new JPopup("You have won!");
        else
            new JPopup("You have lost.");

        gameStageView.returnMenu();
        gameStageView.reset();

    }
}
