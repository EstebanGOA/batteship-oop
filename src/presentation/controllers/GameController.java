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
        this.gameStageView.updateTime(time);
    }

    /**
     * Function that updates the players of the game.
     * @param players An array of all the players that are playing
     */
    public void updateGame(ArrayList<Player> players) {
        this.gameStageView.updateGame(players);
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
                    try {
                        gameManager.attack(player, x, y);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    gameStageView.updateGame(gameManager.getPlayers());
                } else {
                    new JPopup("Error, no es tu turno");
                }
            }
            case "endBattleBtn" -> {
                try {
                    gameManager.stopGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                new JSaveGame();
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
     * @param player The player that has won the match.
     */
    public void returnMenu(Player player) {
        boolean isWinner = player instanceof Human;

        if (isWinner)
            new JPopup("You have won!");
        else
            new JPopup("You have lost.");

        gameStageView.returnMenu("menu");
        gameStageView.reset();

    }
}
