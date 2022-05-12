package presentation.controllers;

import business.GameManager;
import business.entities.Board;
import presentation.views.Cell;
import presentation.views.JPopup;
import presentation.views.SetupStageView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetupStageController implements MouseListener {

    private SetupStageView setupStageView;
    private GameManager gameManager;

    private final String BOAT = "Boat";
    private final String SUBMARINE = "Submarine";
    private final String DESTRUCTOR = "Destructor";
    private final String AIRCRAFT = "Aircraft";

    public SetupStageController(SetupStageView setupStageView, GameManager gameManager) {
        this.setupStageView = setupStageView;
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
        if (gameManager.isSetupStageReady()) {
            gameManager.createIA();
            // setupStageView.switchWindow();
            // gameManager.playGame();
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
