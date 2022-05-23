package business.entities;

/**
 * Class ShipSegment that extends a Tile and it's a part of the hip.
 */
public class ShipSegment extends Tile {

    /**
     * Constructor of ship segment.
     */
    public ShipSegment() {
        super();
    }

    /**
     * Function that returns if a ship segment has been hit.
     * @return Returns a boolean whether the ship segment has been hit or no.
     */
    public boolean isHit() {
        return getTileType() == TileType.HIT;
    }

    /**
     * Function that marks the ship segment as hit,
     * indicating that the ship segment has been sunk.
     */
    public void hit() {
        super.setTileType(TileType.HIT);
    }

}
