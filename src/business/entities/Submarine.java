package business.entities;

/**
 * Class Submarine that extends and is a type of ship.
 */
public class Submarine extends Ship {

    /**
     * Constructor that creates a Submarine with the position, orientation and the size of the boat.
     * @param orientation A string with the orientation of the boat.
     * @param position An array of integers with the coordinates of the boat.
     */
    public Submarine(String orientation, int[] position) {
        super(orientation, position, 3);
    }
}
