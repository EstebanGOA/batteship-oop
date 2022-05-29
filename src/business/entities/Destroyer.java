package business.entities;

/**
 * Class Destroyer that extends and is a type of ship.
 */
public class Destroyer extends Ship {

    /**
     * Constructor that creates a Destroyer with the position, orientation and the size of the boat.
     * @param orientation A string with the orientation of the boat.
     * @param position An array of integers with the coordinates of the boat.
     */
    public Destroyer(String orientation, int[] position) {
        super(orientation, position, 4);
    }

}
