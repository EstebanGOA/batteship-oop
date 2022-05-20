package presentation.controllers;

import business.GameManager;
import business.entities.Board;
import business.entities.Player;
import presentation.views.Cell;
import presentation.views.GameStageView;
import presentation.views.JPopup;
import presentation.views.SetupStageView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SetupStageController implements MouseListener {

    private SetupStageView setupStageView;
    private GameStageView gameStageView;
    private GameManager gameManager;

    public SetupStageController(SetupStageView setupStageView, GameStageView gameStageView, GameManager gameManager) {
        this.setupStageView = setupStageView;
        this.gameStageView = gameStageView;
        this.gameManager = gameManager;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (((JComponent) e.getSource()).getName()) {

            case "cell" -> {
                Cell cell = (Cell) e.getSource();
                processShipPlacement(cell);
            }
            case "start_attack_button" -> {
                isSetupStageReady();
            }
        }


    }

    private void isSetupStageReady() {
        int numberOfEnemies = setupStageView.getNumberOfEnemies();
        if (gameManager.isSetupStageReady()) {
            gameManager.createIA(numberOfEnemies);
            setupStageView.switchWindow();
            gameStageView.paintLayout(numberOfEnemies);
            ArrayList<Player> players = gameManager.getPlayers();
            gameStageView.paintGameStatus(players);
            // setupStageView.switchWindow();
            // gameManager.playGame();
        } else {
            new JPopup("Error, all ships must be placed");
        }
    }

    private void processShipPlacement(Cell cell) {

        int[] coords = cell.getCoordinates();
        String shipSelected = setupStageView.getShipSelected();
        String orientation = setupStageView.getOrientation();

        Board board = gameManager.insertShip(coords, shipSelected, orientation);

        if (board != null) {
            setupStageView.paintShip(board);
        } else {
            new JPopup("Error, ship can not be placed there!");
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
