package business.entities;

import com.google.gson.JsonElement;

import java.awt.*;

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

    public void changeColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
