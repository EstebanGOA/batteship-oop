package business.entities;

public enum TileType {

    WATER(0),
    SHIP(1),
    HIT(2),
    MISS(3);

    private int value;

    private TileType(int value) {
        this.value = value;
    }

}
