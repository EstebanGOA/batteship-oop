package presentation.controllers;

import business.GameManager;
import business.entities.Player;
import presentation.views.Cell;
import presentation.views.GameStageView;
import presentation.views.JPopup;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * GameController class that implements a MouseListener.
 * The GameController is responsible to communicate between the GameManager and the GameStageView.
 */
public class GameController implements MouseListener {

    private GameStageView gameStageView;
    private GameManager gameManager;

    /**
     * Constructor of GameController
     * @param gameStageView The GameStageView  that controls the view of the game.
     * @param gameManager The GameManager that controls the logic of the game.
     */
    public GameController(GameStageView gameStageView, GameManager gameManager) {
        this.gameStageView = gameStageView;
        this.gameManager = gameManager;
    }

    /**
     * Function that updates the time of the game.
     * @param time A string with the time.
     */
    public void updateTimer(String time) {
        SwingUtilities.invokeLater(()->this.gameStageView.updateTime(time));
    }

    /**
     * Function that updates the players of the game.
     * @param players An array of all the players that are playing
     */
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

    /**
     * A function that returns to the menu when the game ends, also pops up a message regarding the game.
     * This message can be either "You have won!" or "You have lost" depending on the outcome of the game.
     * @param winner The position on the array of the player that win the game..
     */
    public void returnMenu(int winner) {

        boolean isWinner = winner == 0;

        if (isWinner)
            new JPopup("You have won!");
        else
            new JPopup("You have lost.");

        gameStageView.returnMenu();
        gameStageView.reset();

    }
}
