package business.entities;

public class Tile {

    private TileType tileType;

    /**
     * Constructor of Tile
     */
    public Tile() {
        this.tileType = TileType.WATER;
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

}
