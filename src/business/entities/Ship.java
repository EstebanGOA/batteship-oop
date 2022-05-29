package business.entities;

/**
 * Abstract class with the common variables of the ships.
 */
public abstract class Ship {

    private final int size;
    private final String orientation;
    private final int[] position;
    private final ShipSegment[] shipSegments;

    /**
     * Constructor of Ship
     *
     * @param orientation Orientation of the ship.
     * @param position Position of the ship.
     */
    public Ship(String orientation, int[] position, int size) {
        this.orientation = orientation;
        this.position = position;
        this.shipSegments = new ShipSegment[size];
        this.size = size;
    }

    /**
     * Function that returns the orientation of the ship.
     *
     * @return Returns a string with the orientation of the ship.
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * Functions that returns the coordinates where the ship is located/placed.
     * The coordinates where the ship is located, in the first position we have the coordinate regarding the X
     * and the second coordinate regarding the Y.
     *
     * @return Returns an int[] with the position of the ship.
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Function that gets the size of the ship
     * @return An integer with the size of the ship
     */
    public int getSize() {
        return size;
    }



    /**
     * Function that checks if the ships is sunk.
     * A ship is sunk when all its fragments are hit.
     * @return Returns a boolean regarding if the ship is sunk.
     */
    public boolean isSunk() {
        int counter = 0;
        for (ShipSegment segment : shipSegments) {
            if (segment.isHit()) {
                counter++;
            }
        }
        return counter == size;
    }

    /**
     * Function that add a segment to the ship
     * @param shipSegment The ships segment added to the ship
     */
    public void addSegment(ShipSegment shipSegment) {
        for (int i = 0; i < this.shipSegments.length; i++) {
            if (this.shipSegments[i] == null) {
                this.shipSegments[i] = shipSegment;
                break;
            }
        }
    }
}