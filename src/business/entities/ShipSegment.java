package business.entities;

public class ShipSegment extends Tile {

    private Ship ship;

    public ShipSegment(Ship ship) {
        super();
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isHit() {
        return getTileType() == TileType.HIT;
    }

    public void hit() {
        super.setTileType(TileType.HIT);
    }

}
