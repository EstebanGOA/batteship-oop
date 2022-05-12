package business.entities;

/**
 * Clase abstracta con las variables comunes entre los jugadores.
 */
abstract public class Player {

    Board board;

    /**
     * Las posiciones de los barcos dentro del array están predefinidos de la siguiente manera:
     * 0 -> Boat
     * 1 -> Submarine
     * 2 -> Submarine
     * 3 -> Destroyer
     * 4 -> Aircrafter
     */
    Ship[] ships;

    /**
     * Constructor de Player.
     * @param board Board donde estarán los barcos del jugador.
     */
    public Player(Board board) {
        this.board = board;
        this.ships = new Ship[5];
    }

    /**
     * Devuelve el tablero del jugador.
     * @return Board donde tiene colocado los barcos el jugador.
     */
    public Board getBoard() {
        return board;
    }

    public boolean insertShip(int[] cords, String shipSelected, String orientation) {

        Ship ship;
        int position;

        switch (shipSelected) {
            case "Boat" -> {
                if (ships[0] != null) {
                    return false;
                }
                ship = new Boat(orientation, cords);
                position = 0;
            }
            case "Submarine" -> {

                if (ships[1] != null && ships[2] != null) {
                    return false;
                }

                ship = new Submarine(orientation, cords);

                if (ships[1] != null) {
                    position = 2;
                } else {
                    position = 1;
                }

            }
            case "Destroyer" -> {
                if (ships[3] != null) {
                    return false;
                }
                ship = new Destroyer(orientation, cords);
                position = 3;
            }
            case "Aircraft" -> {
                if (ships[4] != null) {
                    return false;
                }
                position = 4;
                ship = new AircraftCarrier(orientation, cords);
            }
            default -> {
                ship = null;
                position = 0;
            }
        }

        if (ship != null) {
            if (board.placeShip(ship)) {
                ships[position] = ship;
                return true;
            }
        }

        return false;

    }

}
