package presentation.views;

import presentation.controllers.GameController;

import javax.swing.*;

import java.awt.*;

public class JSaveGame extends JFrame {

    public JSaveGame () {

        setLocationRelativeTo(null);
        setSize(new Dimension(250,50));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel jLabel = new JLabel("Do you want to save the game?");
        JButton jButton = new JButton("Save");
        JButton jButton1 = new JButton("Cancel");

        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel.add (jLabel);

        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        jPanel.add (jButton);

        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        jPanel.add (jButton1);

        add(jPanel);
        setVisible(true);
    }
}
