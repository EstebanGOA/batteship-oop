package presentation.controllers;

import business.GameManager;
import business.entities.Player;
import presentation.views.Cell;
import presentation.views.GameStageView;
import presentation.views.JPopup;
import presentation.views.JSaveGame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameController implements MouseListener {

    private GameStageView gameStageView;
    private GameManager gameManager;

    public GameController(GameStageView gameStageView, GameManager gameManager) {
        this.gameStageView = gameStageView;
        this.gameManager = gameManager;
    }

    public void updateTimer(String time) {
        this.gameStageView.updateTime(time);
    }

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
                    gameManager.attack(player, x, y);
                    gameStageView.updateGame(gameManager.getPlayers());
                } else {
                    new JPopup("Error, no es tu turno");
                }
            }
            case "endBattleBtn" -> {
                gameManager.stopGame();
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
}
