package presentation.views;


import com.sun.tools.javac.Main;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StatisticsView extends JPanel {

    private MainView mainView;

    private final String BACK_BUTTON_IMAGE    = "sprites/back_button.png";

    private final Color BACKGROUND_COLOR = new Color(39,152,213);



    private final String FONT = "fonts/Poppins-Bold.ttf";

    private JImagePanel backButton;

    private JComboBox userList;

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

        backButton = new JImagePanel(BACK_BUTTON_IMAGE);
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

        // Must change the data on pieChart and BarChart
        PieChart pieChart = new PieChart(0);
        pieChart.setPreferredSize(new Dimension(400, 400));

        barChart = new BarChart();
        barChart.setPreferredSize(new Dimension(400, 300));




        JPanel nameBackgroundPanel = new JImagePanel("sprites/name_background.png");
        nameBackgroundPanel.setOpaque(false);
        nameBackgroundPanel.setLayout(new BorderLayout());
        nameBackgroundPanel.setPreferredSize(new Dimension(350,75));

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(font);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        nameBackgroundPanel.add(usernameLabel);



        userList = new JComboBox();


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

    public void menuController(MouseListener mouseListener) {
        backButton.addMouseListener(mouseListener);

    }

    public void menuView() {
        mainView.switchPanel("menu");
    }

    public void addItem(String s) {

        userList.addItem(s);
    }

    public void addBars(int i) {
        barChart.addBar(i);
    }



}
