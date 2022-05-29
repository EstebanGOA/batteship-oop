package presentation.controllers;

import business.UserManager;
import presentation.views.StatisticsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * StatisticsController class that implements a MouseListener and a ActionListener.
 * The StatisticsController is responsible for displaying the view with user statistics.
 */
public class StatisticsController implements MouseListener, ActionListener {

    private UserManager userManager;
    private StatisticsView statisticsView;

    public StatisticsController(UserManager userManager, StatisticsView statisticsView) {
        this.userManager = userManager;
        this.statisticsView = statisticsView;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "back" ->  statisticsView.menuView();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = statisticsView.getStringSelected();
        statisticsView.updatePieChart(userManager.getWinrate(name));
        statisticsView.addBars(userManager.getAttacks(name));
    }
}
