package business.entities;

/**
 * Class AircraftCarrier that extends and is a type of ship.
 */
public class AircraftCarrier extends Ship {

    /**
     * Constructor that creates a AircraftCarrier with the position, orientation and the size of the boat.
      * @param orientation A string with the orientation of the boat.
     * @param position An array of integers with the coordinates of the boat.
     */
    public AircraftCarrier(String orientation, int[] position) {
        super(orientation, position, 5);
    }

}
