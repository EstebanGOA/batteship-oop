package business.entities;

/**
 * Enumeration TileType.
 */
public enum TileType {

    /**
     * Water tile.
     */
    WATER(0),

    /**
     * Ship tile.
     */
    SHIP(1),

    /**
     * Hit tile.
     */
    HIT(2),

    /**
     * Miss tile.
     */
    MISS(4);


    private int value;

    /**
     * Constructor of the tile type.
     * @param value An integer with the value.
     */
    TileType(int value) {
        this.value = value;
    }

    /**
     * Function that returns the value.
     * @return
     */
    public int getValue() {
        return value;
    }
}
