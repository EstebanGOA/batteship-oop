package presentation.controllers;

import business.UserManager;
import presentation.views.MenuView;
import presentation.views.StatisticsView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StatisticsController implements MouseListener {

    private UserManager userManager;
    private StatisticsView statisticsView;

    public StatisticsController(UserManager userManager, StatisticsView statisticsView) {
        this.userManager = userManager;
        this.statisticsView = statisticsView;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "back" -> statisticsView.menuView();

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
