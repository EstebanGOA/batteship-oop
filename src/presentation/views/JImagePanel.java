package presentation.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Custom JPanel that renders an image in the background
public class JImagePanel extends JPanel {

    // The image to render
    private BufferedImage image;

    private float scale;
    private float angle;
    private boolean isRotated;

    public void switchImage(SpritePath path) {
        try {
            image = ImageIO.read(new File(path.toString()));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Constructor with parameters
    public JImagePanel(SpritePath path, float scale, boolean isRotated) {
        try {
            image = ImageIO.read(new File(path.toString()));
            this.angle = 0;
            this.scale = scale;
            this.isRotated = isRotated;
        } catch (IOException e) {
            // Not properly managed, sorry!
            e.printStackTrace();
        }
    }

    public JImagePanel(SpritePath path) {
        try {
            image = ImageIO.read(new File(path.toString()));
            this.angle = 0;
            this.scale = 1;
            this.isRotated = false;
        } catch (IOException e) {
            // Not properly managed, sorry!
            e.printStackTrace();
        }
    }

    public void rotateImage() {
        if (angle != 0) {
            angle = 0;
            setPreferredSize(new Dimension (40, 80));
        } else {
            angle = 90;
            setPreferredSize(new Dimension (80, 40));
        }
        repaint();
    }

    // Paint the image in the background, with the size the layout assigns to the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isRotated) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
            AffineTransform at = new AffineTransform();
            at.translate(getWidth() / 2, getHeight() / 2);
            at.rotate(Math.toRadians(angle));
            at.scale(scale, scale);
            at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image, at, null);
        }
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}