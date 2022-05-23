package business.entities;

/**
 * Abstract class with the common variables of the players.
 */
abstract public class Player {

    Board board;

    Ship[] ships;

    /**
     * Constructor of Player.
     * @param board A board with the ships of the player.
     */
    public Player(Board board) {
        this.board = board;
        this.ships = new Ship[5];
    }

    /**
     * Return the board of the player.
     * @return Returns a board where the user has placed the ships
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Function that inserts a ships.
     * @param cords X and Y coordinates of the start of the ship.
     * @param shipSelected The ships that are going to be placed.
     * @param orientation  A string of the orientation of the ships, vertical or horizontal.
     * @return Returns the board with the ships placed.
     */
    public Board insertShip(int[] cords, String shipSelected, String orientation) {

        Ship ship = null;
        int index = 0;

        switch (shipSelected) {
            case "Boat" -> {
                ship = new Boat(orientation, cords);
                if (ships[0] != null) {
                    board.remove(ships[0]);
                }
                index = 0;
            }
            case "Submarine1" -> {
                ship = new Submarine(orientation, cords);
                if (ships[1] != null) {
                    board.remove(ships[1]);
                }
                index = 1;
            }
            case "Submarine2" -> {
                ship = new Submarine(orientation, cords);
                if (ships[2] != null) {
                    board.remove(ships[2]);
                }
                index = 2;
            }
            case "Destructor" -> {
                ship = new Destroyer(orientation, cords);
                if (ships[3] != null) {
                    board.remove(ships[3]);
                }
                index = 3;
            }
            case "Aircraft" -> {
                ship = new AircraftCarrier(orientation, cords);
                if (ships[4] != null) {
                    board.remove(ships[4]);
                }
                index = 4;
            }
        }

        if (ship != null) {
            if (board.placeShip(ship)) {
                ships[index] = ship;
                return board;
            }
        }
        return null;
    }

    /**
     * Function that checks if all the ships are placed, if true all ships are placed, else all ships aren't placed
     * @return Return the boolean regarding if all the ships are placed
     */
    public boolean allShipPlaced() {
        boolean flag = true;
        for (Ship ship : ships) {
            if (ship == null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Function that checks if all the ships are sunk, if true all ships are sunk, else all ships aren't sunk
     * @return Return the boolean regarding if all the ships are sunk
     */
    public boolean allShipsDestroyed() {

        int shipsDestroyed = 0;

        for( int numShips = 0; numShips < 5; numShips++) {
                if (ships[numShips].isSunk()) {
                    shipsDestroyed++;
                }
        }
        return shipsDestroyed == 5;
    }

}
