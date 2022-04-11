import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuView extends JFrame {

    public MainMenuView() {

        initializeWindow();

        Font font = initializeFont();

        // ------------------------ Background Image ------------------------ //
        // ---------- Grid layout to divide the window in 3 columns --------- //

        JImagePanel backgroundPanel = new JImagePanel("sprites/background_main_menu.png");
            backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // --------------------------- Left Column -------------------------- //

        JPanel leftColumn = new JPanel (new GridBagLayout());
            leftColumn.setOpaque(false);

        GridBagConstraints gbcLeftColumn = new GridBagConstraints();

            JImagePanel nameBackgroundPanel = new JImagePanel("sprites/name_background.png");
                nameBackgroundPanel.setOpaque(false);
                nameBackgroundPanel.setLayout(new BorderLayout());
                nameBackgroundPanel.setPreferredSize(new Dimension(350,75));

                JLabel usernameLabel = new JLabel("Tomas");
                    usernameLabel.setFont(font);
                    usernameLabel.setForeground(Color.white);

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

        JImagePanel trophiesBackground = new JImagePanel("sprites/trophies_background.png");
            trophiesBackground.setOpaque(false);
            trophiesBackground.setLayout(new BorderLayout());
            trophiesBackground.setPreferredSize(new Dimension(200, 75));

            JLabel trophiesLabel = new JLabel("0");
                trophiesLabel.setFont(font);
                trophiesLabel.setForeground(Color.white);

             trophiesBackground.add(trophiesLabel);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 0;
        rightColumn.add(addSeparator(250,0), gbcRightColumn);

        gbcRightColumn.gridx = 1; gbcRightColumn.gridy = 0;
        gbcRightColumn.anchor = GridBagConstraints.FIRST_LINE_START;
        rightColumn.add(trophiesBackground, gbcRightColumn);

        gbcRightColumn.gridx = 0; gbcRightColumn.gridy = 1;
        rightColumn.add(addSeparator(0, 650));

        // ------------------------ Display Objects ------------------------ //

        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(leftColumn, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        backgroundPanel.add(midColumn, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        backgroundPanel.add(rightColumn, gbc);

        add(backgroundPanel);

        setVisible(true);
    }

    public void initializeWindow () {
        setSize(1280, 720);

        setTitle("Battleship v1.0.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
}
