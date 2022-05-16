package business.entities;

public class ShipSegment extends Tile {

    public ShipSegment() {
        super();
    }

    public boolean isHit() {
        return getTileType() == TileType.HIT;
    }

    public void hit() {
        super.setTileType(TileType.HIT);
    }

}
