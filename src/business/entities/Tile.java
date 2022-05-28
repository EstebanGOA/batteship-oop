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
     *
     * @return
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     *
     * @param tileType
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
