package presentation.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

// Custom JPanel that renders an image in the background
public class JImagePanel extends JPanel {

    // The image to render
    private BufferedImage image;
    private float angle;

    // Constructor with parameters
    public JImagePanel(String path) {

        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            // Not properly managed, sorry!
            e.printStackTrace();
        }
    }

    public JImagePanel(String path, float angle) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            // Not properly managed, sorry!
            e.printStackTrace();
        }

        this.angle = angle;
    }

    // Paint the image in the background, with the size the layout assigns to the panel
    @Override
    protected void paintComponent(Graphics g) {
        if (angle == 0) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("ROTATING");

            AffineTransform at = AffineTransform.getTranslateInstance(100,100);
            at.rotate(Math.toRadians(angle));

            Graphics2D g2d = (Graphics2D) g;
            //g2d.rotate(Math.toRadians(angle));
            g2d.drawImage(image, at, null);
        }

        super.paintComponent(g);

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}