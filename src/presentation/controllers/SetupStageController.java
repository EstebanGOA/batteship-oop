package presentation.controllers;

import business.GameManager;
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

        Cell cell = (Cell) e.getSource();
        int[] coords = cell.getCoordinates();
        String shipSelected = setupStageView.getShipSelected();
        String orientation = setupStageView.getOrientation();

        if (gameManager.insertShip(coords, shipSelected, orientation)) {
            System.out.println("Barco insertado");
        } else {
            System.out.println("No se puede insertar");
            // Show popup
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
