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

}
