package presentation.views;

import presentation.controllers.StatisticsController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StatisticsView extends JPanel {

    private MainView mainView;


    private final Color BACKGROUND_COLOR = new Color(39,152,213);

    // Change this
    private final String FONT = "fonts/Poppins-Bold.ttf";

    private JImagePanel backButton;

    private JComboBox userList;

    private PieChart pieChart;
    private BarChart barChart;

    public StatisticsView(MainView mainView) {

        this.mainView = mainView;

        initializeWindow();
        Font font = initializeFont();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);

        // Box Layout to add space on the left of the back button
        JPanel spaceAndBackButton = new JPanel();
        spaceAndBackButton.setOpaque(false);
        spaceAndBackButton.setLayout(new BoxLayout(spaceAndBackButton, BoxLayout.X_AXIS));

        // Back Button Image
        backButton = new JImagePanel(SpritePath.BACK_BUTTON);
        backButton.setPreferredSize(new Dimension(75, 0));
        backButton.setOpaque(false);
        backButton.setName("back");

        // Window Title
        JLabel jLabel = new JLabel();
        jLabel.setLayout(new BorderLayout());
        jLabel.setFont(font);
        jLabel.setOpaque(false);
        jLabel.setText("Statistics");
        jLabel.setForeground(Color.white);
        jLabel.setBorder(BorderFactory.createEmptyBorder());
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));

        spaceAndBackButton.add(addSeparator(75,0));
        spaceAndBackButton.add(backButton);

        jLabel.add(spaceAndBackButton, BorderLayout.WEST);
        topPanel.add(jLabel, BorderLayout.CENTER);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(BACKGROUND_COLOR);
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setPreferredSize(new Dimension(1280, 720));

        pieChart = new PieChart();
        pieChart.setPreferredSize(new Dimension(400, 400));

        barChart = new BarChart();
        barChart.setPreferredSize(new Dimension(400, 300));

        JPanel nameBackgroundPanel = new JPanel();
        nameBackgroundPanel.setOpaque(false);
        nameBackgroundPanel.setLayout(new BorderLayout());
        nameBackgroundPanel.setPreferredSize(new Dimension(350,75));

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(font);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        nameBackgroundPanel.add(usernameLabel);

        userList = new JComboBox();
        userList.setName("users");

        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        grid.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 2;
        grid.add(pieChart, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        grid.add(addSeparator(25,0), gbc);

        gbc.gridx = 2; gbc.gridy = 2;
         grid.add(barChart, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        grid.add(addSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy =0;
        gbc.gridwidth = 2;

        grid.add(nameBackgroundPanel, gbc);

        gbc.gridx = 1; gbc.gridy =0;
        grid.add(userList, gbc);

        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(grid, BorderLayout.CENTER);

        add(backgroundPanel);

        setVisible(true);
    }

    /**
     * Method to initialize the font of the settings view.
     *
     * @return the initialized font.
     */
    public Font initializeFont () {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(FONT)).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(FONT)));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font;
    }
    /**
     * Method to create a separator between two panels.
     *
     * @param width  width of the separator panel.
     * @param height height of the separator panel.
     * @return the separator panel.
     */
    public JPanel addSeparator (int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box. createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }

    /**
     * Method to initialize the window of the settings view.
     */
    public void initializeWindow () {
        setSize(1280, 720);
    }

    /**
     * Method to add a StatisticsController as a listener.
     * @param statisticsController The statistics controller.
     */
    public void menuController(StatisticsController statisticsController) {
        backButton.addMouseListener(statisticsController);
        userList.addActionListener(statisticsController);
    }

    /**
     * Function used to return to the menu.
     */
    public void menuView() {
        userList.removeAllItems();
        mainView.switchPanel("menu");
    }

    /**
     * Function that add a name to the JComboBox.
     * @param s The string with the name.
     */
    public void addItem(String s) {
        userList.addItem(s);
    }

    /**
     * Function that adds all the bars to create the bar chart.
     * @param num_attacks
     */
    public void addBars(ArrayList<Integer> num_attacks) {
        barChart.clearBars();
        for (int i : num_attacks) {
            barChart.addBar(i);
        }
    }

    /**
     * Function that updates the pie chart.
     * @param stats The new stats.
     */
    public void updatePieChart(int[] stats) {
        pieChart.setWinrate(stats);
        pieChart.repaint();
    }

    /**
     * Function that returns the item selected of the JComboBox
     * @return A string with the name.
     */
    public String getStringSelected() {
        if(userList.getItemCount() == 0) {
            return null;
        }
        return userList.getItemAt(userList.getSelectedIndex()).toString();
    }


}
