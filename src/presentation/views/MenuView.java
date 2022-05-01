package presentation.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel {

    private final MainView mainView;

    private String username = "";

    private JImagePanel settingsButton;

    public MenuView(MainView mainView) {

        this.mainView = mainView;

        Font font = initializeFont();

        // ------------------------ Background Image ------------------------ //
        // ---------- Grid layout to divide the window in 3 columns --------- //

        JPanel backgroundPanel = new JPanel();
            backgroundPanel.setPreferredSize(new Dimension(1280, 720));
            backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // --------------------------- Left Column -------------------------- //

        JPanel leftColumn = new JPanel (new GridBagLayout());
            leftColumn.setOpaque(false);

        GridBagConstraints gbcLeftColumn = new GridBagConstraints();

            JPanel nameBackgroundPanel = new JImagePanel("sprites/name_background.png");
                nameBackgroundPanel.setOpaque(false);
                nameBackgroundPanel.setLayout(new BorderLayout());
                nameBackgroundPanel.setPreferredSize(new Dimension(350,75));

                    JLabel usernameLabel = new JLabel(username);
                    usernameLabel.setFont(font);
                    usernameLabel.setForeground(Color.white);
                    usernameLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

            nameBackgroundPanel.add(usernameLabel);

        gbcLeftColumn.gridx = 0; gbcLeftColumn.gridy = 0;
        gbcLeftColumn.anchor = GridBagConstraints.FIRST_LINE_START;
        leftColumn.add(nameBackgroundPanel, gbcLeftColumn);

        gbcLeftColumn.gridx = 1; gbcLeftColumn.gridy = 0;
        leftColumn.add(addSeparator(100,0), gbcLeftColumn);

        gbcLeftColumn.gridx = 0; gbcLeftColumn.gridy = 1;
        leftColumn.add(addSeparator(0,570), gbcLeftColumn);

        // --------------------------- Mid Column -------------------------- //

        JPanel midColumn = new JPanel (new GridBagLayout());
            midColumn.setOpaque(false);

        GridBagConstraints gbcMidColumn = new GridBagConstraints();

        JImagePanel newBattleButton = new JImagePanel("sprites/new_battle_button.png");
            newBattleButton.setPreferredSize(new Dimension(350,125));
            newBattleButton.setOpaque(false);

        JImagePanel loadBattleButton = new JImagePanel("sprites/load_battle_button.png");
            loadBattleButton.setPreferredSize(new Dimension(350,125));
            loadBattleButton.setOpaque(false);

        gbcMidColumn.gridx = 0; gbcMidColumn.gridy = 0;
        midColumn.add(newBattleButton, gbcMidColumn);

        gbcMidColumn.gridx = 0; gbcMidColumn.gridy = 1;
        midColumn.add(addSeparator(0,50), gbcMidColumn);

        gbcMidColumn.gridx = 0; gbcMidColumn.gridy = 2;
        midColumn.add(loadBattleButton, gbcMidColumn);

        // --------------------------- Right Column -------------------------- //

        JPanel rightColumn = new JPanel (new GridBagLayout());
            rightColumn.setOpaque(false);

        GridBagConstraints gbcRightColumn = new GridBagConstraints();

        // Trophies Panel

        JImagePanel trophiesBackground = new JImagePanel("sprites/trophies_background.png");
            trophiesBackground.setOpaque(false);
            trophiesBackground.setLayout(new BorderLayout());
            trophiesBackground.setPreferredSize(new Dimension(200, 75));

            JLabel trophiesLabel = new JLabel("0");
                trophiesLabel.setFont(font);
                trophiesLabel.setForeground(Color.white);
                trophiesLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

             trophiesBackground.add(trophiesLabel);

        // Settings Button

            settingsButton = new JImagePanel("sprites/settings_button.png");
            settingsButton.setPreferredSize(new Dimension(75,75));
            settingsButton.setOpaque(false);
            settingsButton.setName("settings");

        // Statistics Button

        JImagePanel statisticsButton = new JImagePanel("sprites/statistics_button.png");
            statisticsButton.setPreferredSize(new Dimension(75,75));
            statisticsButton.setOpaque(false);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 0;
        rightColumn.add(addSeparator(200,0), gbcRightColumn);

        gbcRightColumn.gridx = 1; gbcRightColumn.gridy = 0;
        gbcRightColumn.anchor = GridBagConstraints.FIRST_LINE_START;
        rightColumn.add(trophiesBackground, gbcRightColumn);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 1;
        rightColumn.add(addSeparator(0,15), gbcRightColumn);

        gbcRightColumn.gridx = 1; gbcRightColumn.gridy = 2;
        gbcRightColumn.anchor = GridBagConstraints.FIRST_LINE_END;
        rightColumn.add(settingsButton, gbcRightColumn);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 3;
        rightColumn.add(addSeparator(0,15), gbcRightColumn);

        gbcRightColumn.gridx = 1; gbcRightColumn.gridy = 4;
        gbcRightColumn.anchor = GridBagConstraints.FIRST_LINE_END;
        rightColumn.add(statisticsButton, gbcRightColumn);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 5;
        rightColumn.add(addSeparator(0, 375), gbcRightColumn);

        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(leftColumn, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        backgroundPanel.add(midColumn, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        backgroundPanel.add(rightColumn, gbc);

        add(backgroundPanel);

        //setVisible(true);
    }

    public void initializeWindow () {
        setSize(1280, 720);
    }

    public Font initializeFont () {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    public JPanel addSeparator (int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box. createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }

    public void menuController(MouseListener mouseListener) {
        settingsButton.addMouseListener(mouseListener);
    }
    public void settingsView() {
        mainView.switchPanel("settings");
    }


    public void setUsername(String username) {
        this.username = username;
    }
}
