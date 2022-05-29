package business.entities;

import java.awt.*;

/**
 * Class Tile.
 */
public class Tile {

    private TileType tileType;
    private Color color;

    /**
     * Constructor of Tile
     */
    public Tile() {
        this.tileType = TileType.WATER;
        this.color = Color.BLUE;
    }

    /**
     *  Function that gets the tile type.
     * @return Returns the tile type.
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     *  Function that sets the tile type.
     * @param tileType that is set in the tile.
     */
    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    /**
     * Function that changes the color of the tile.
     * @param color The new color.
     */
    public void changeColor(Color color) {
        this.color = color;
    }

    /**
     * Function that gets the color of the tile
     * @return Returns the color.
     */
    public Color getColor() {
        return color;
    }
}
