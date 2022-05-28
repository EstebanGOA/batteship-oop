package presentation.views;



import presentation.controllers.StatisticsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
    public JPanel addSeparator (int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box. createRigidArea(new Dimension(width, height));
        space.add(rigidArea);

        return space;
    }

    public void initializeWindow () {
        setSize(1280, 720);
    }

    public void menuController(StatisticsController statisticsController) {
        backButton.addMouseListener(statisticsController);
        userList.addActionListener(statisticsController);
    }

    public void menuView() {
        mainView.switchPanel("menu");
    }

    public void addItem(String s) {
        userList.addItem(s);
    }

    public void addBars(ArrayList<Integer> num_attacks) {
        barChart.clearBars();
        for (int i : num_attacks) {
            barChart.addBar(i);
        }
    }



    public void updatePieChart(int[] stats) {
        pieChart.setWinrate(stats);
        pieChart.repaint();
    }



    public String getStringSelected() {
        return userList.getItemAt(userList.getSelectedIndex()).toString();
    }

}