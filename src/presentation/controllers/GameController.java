package presentation.controllers;

import business.GameManager;
import business.entities.IA;
import business.entities.Player;
import presentation.views.Cell;
import presentation.views.GameStageView;
import presentation.views.JPopup;
import presentation.views.JSaveGame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "cell" -> {
                Cell cell = (Cell) e.getSource();
                int x = cell.getCoordinates()[0];
                int y = cell.getCoordinates()[1];
                System.out.println("x: " + x + "\ny: " + y);
                if (gameManager.getTurn() == 0) {
                    Player player = gameManager.getPlayers().get(0);
                    for (Player objective : gameManager.getPlayers()) {
                        if (objective instanceof IA) {
                            player.attack(objective, x, y);
                        }
                    }
                    gameStageView.paintGameStatus(gameManager.getPlayers());
                } else {
                    new JPopup("Error, no es tu turno");
                }
            }
            case "endBattleBtn" -> {
                gameManager.stopTimer();
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
}
