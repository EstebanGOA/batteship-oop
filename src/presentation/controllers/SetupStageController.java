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

/**
 * SettingsController class that implements a MouseListener.
 * The SettingsController it is in charge of managing the correct functioning of the gameStageView.
 */
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

    /**
     * Function check that all the ships are positioned on the board to be able to start with the gameStageView.
     */
    private void isSetupStageReady() {
        int numberOfEnemies = setupStageView.getNumberOfEnemies();

        if (gameManager.isSetupStageReady()) {
            gameManager.createIA(numberOfEnemies);
            gameStageView.paintLayout(numberOfEnemies);
            ArrayList<Player> players = gameManager.getPlayers();
            gameStageView.updateGame(players);
            gameManager.startGame();
            setupStageView.switchWindow();
            setupStageView.reset();

        } else {
            new JPopup("Error, all ships must be placed");
        }

    }

    /**
     * Function check that the placement of the ships on the board is valid.
     * @param cell A Cell object with the user board information.
     */
    private void processShipPlacement(Cell cell) {

        int[] coords = cell.getCoordinates();
        String shipSelected = setupStageView.getShipSelected();
        String orientation = setupStageView.getOrientation();

        Board board = gameManager.insertShip(coords, shipSelected, orientation);

        if (board != null) {
            setupStageView.updateBoard(board);
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
