package business.entities;

/**
 * Class ShipSegment that extends a Tile and it's a part of the hip.
 */
public class ShipSegment extends Tile {

    private Ship ship;

    /**
     * Constructor of ShipSegment.
     * @param ship
     */
    public ShipSegment(Ship ship) {
        super();
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    /**
     * Function that returns if a ship segment has been hit.
     * @return Returns a boolean whether the ship segment has been hit or no.
     */
    public boolean isHit() {
        return getTileType() == TileType.HIT;
    }
}
