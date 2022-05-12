package business.entities;

/**
 * Clase abstracta con las variables comunes entre todos los barcos.
 */
public abstract class Ship {

    private final int size;
    private final String orientation;
    private final int[] position;
    private final ShipSegment[] shipSegments;

    /**
     * Constructor de Ship
     *
     * @param orientation Orientación del barco.
     * @param position Posición del barco.
     */
    public Ship(String orientation, int[] position, int size) {
        this.orientation = orientation;
        this.position = position;
        this.shipSegments = new ShipSegment[size];
        this.size = size;
    }

    /**
     * Función que devuelve la orientación del barco.
     *
     * @return String con la orientación del barco.
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * Función que devuelve las coordenadas en la que está colocado el barco.
     * Las coordenadas donde está esta el barco, la primera posición pertenece a la X, y la segunda a la Y.
     *
     * @return int[] con las posiciones del barco.
     */
    public int[] getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public ShipSegment[] getShipSegments() {
        return shipSegments;
    }

    public boolean isSunk() {
        int counter = 0;
        for (ShipSegment segment : shipSegments) {
            if (segment.isHit()) {
                counter++;
            }
        }
        return counter == size;
    }

    public void addSegment(ShipSegment shipSegment) {
        for (int i = 0; i < this.shipSegments.length; i++) {
            if (this.shipSegments[i] == null) {
                this.shipSegments[i] = shipSegment;
                break;
            }
        }
    }
}
