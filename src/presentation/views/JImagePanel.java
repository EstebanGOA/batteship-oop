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

    private String path;
    private float scale;
    private float angle;
    private boolean isRotated;

    /**
     *
     * Method to switch an image.
     *
     * @param path Path of the new image.
     *
     */

    public void switchImage(SpritePath path) {
        try {
            if (path.equals(SpritePath.WATER)) {
                isRotated = false;
            }
            if (!this.path.equals(path.toString())) {
                image = ImageIO.read(new File(path.toString()));
                this.path = path.toString();
            }
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Method to switch image for the table with the ships parts.
     *
     * @param boatPieces path of the boat piece.
     * @param piece number of pieces.
     * @param scale scale of the piece.
     * @param orientation orientation of boat piece.
     *
     */

    public void switchImage(SpritePath boatPieces, int piece, float scale, String orientation) {
        try {

            if (orientation.equals("horizontal")) {
                this.angle = 0;
                isRotated = false;
            } else {
                this.angle = 90;
                this.scale = scale;
                isRotated = true;
            }

            if (!this.path.equals(boatPieces.getPath(piece))) {
                image = ImageIO.read(new File(boatPieces.getPath(piece)));
                this.path = boatPieces.getPath(piece);
            }
            repaint();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Constructor of the JImagePanel, to show an image.
     *
     * @param path path of the image that you want to load.
     * @param scale scale of the image.
     * @param isRotated rotation flag.
     *
     */

    public JImagePanel(SpritePath path, float scale, boolean isRotated) {
        try {
            image = ImageIO.read(new File(path.toString()));
            this.path = path.toString();
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
            this.path = path.toString();
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