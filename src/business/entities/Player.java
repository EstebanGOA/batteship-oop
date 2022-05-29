package business.entities;

import java.awt.*;

/**
 * Abstract class with the common variables of the players.
 */
abstract public class Player implements Runnable {

    private Board board;
    private Ship[] ships;
    private boolean attacked[][];

    /* This variables are declared volatile because are changed inside threads so, they are not stored on cache */
    private volatile boolean recharging;
    private volatile boolean alive;
    private volatile int numberOfAttacks;
    private volatile boolean stop;
    private int delay;

    /* It will be used to identify the players on the board */
    private Color color;

    /**
     * Constructor of Player.
     * @param board A board with the ships of the player.
     */
    public Player(Board board, Color color, int delay) {
        this.board = board;
        this.ships = new Ship[5];
        this.recharging = false;
        this.alive = true;
        this.numberOfAttacks = 0;
        this.attacked = new boolean[15][15];
        this.color = color;
        this.delay = delay;
        this.stop = false;
    }

    /**
     * Constructor of player
     * @param board The board of the player.
     * @param recharging A boolean regarding if the player is recharging.
     * @param alive A boolean regarding if the player is alive.
     * @param numberOfAttacks An integer with teh number of attacks.
     * @param attacked A matrix of booleans where the player has already attacked.
     * @param color The color.
     * @param delay An integer with the delay.
     */
    public Player(Board board, boolean recharging, boolean alive, int numberOfAttacks, boolean[][] attacked, Color color, int delay) {
        this.board = board;
        this.ships = new Ship[5];
        this.recharging = recharging;
        this.alive = alive;
        this.numberOfAttacks = numberOfAttacks;
        this.attacked = attacked;
        this.color = color;
        this.delay = delay;
        this.stop = false;
    }

    /**
     * Return the board of the player.
     * @return Returns a board where the user has placed the ships
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Function that gets the matrix of where the player has attacked.
     * @return Returns the matrix of booleans.
     */
    public boolean[][] getAttacked() {
        return attacked;
    }

    /**
     * Function that gets the color.
     * @return Returns the color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Function that get the delay.
     * @return Returns an integer with the delay.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Function that gets if the player is stopped.
     * @return Returns a boolean with the outcome.
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * Function that set the stop.
     * @param stop A boolean with the new stop.
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Function that gets all the ships.
     * @return Returns all the ships.
     */
    public Ship[] getShips() {
        return ships;
    }

    /**
     * Function that inserts ships.
     * @param cords An array with the coordinates.
     * @param shipSelected A string with the name of the ship.
     * @param orientation A string with the orientation of the ship.
     * @return Returns the board.
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
            if (board.placeShip(ship, this)) {
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
     * Function that attacks the ia.
     * @param player The player.
     * @param x The coordinate x of the attack.
     * @param y The coordinate y of the attack
     * @return Returns a boolean regarding the outcome.
     */
    public boolean attack(Player player, int x, int y) {
        return player.getBoard().sendAttack(this, x, y);
    }

    /**
     * Function that checks if a position is attacked already.
     * @param x An integer with the coordinate x.
     * @param y An integer with the coordinate y.
     * @return Returns a boolean depending on the outcome.
     */
    public boolean isAttackedAlready(int x, int y) {
        if (!attacked[x][y]) {
            attacked[x][y] = true;
            numberOfAttacks++;
            return false;
        } else {
            return true;
        }
    }

    /**
     * Function that sets the recharging.
     * @param recharging A boolean with the new reacharging.
     */
    public void setRecharging(boolean recharging) {
        this.recharging = recharging;
    }

    /**
     * Function that gets recharging.
     * @return Returns a boolean regarding if the player is recharging.
     */
    public boolean isRecharging() {
        return recharging;
    }

    /**
     * Function that check if the player is alive.
     * @return Returns a boolean regarding the outcome.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Function that sets if the player is alive.
     * @param alive A boolean with the new status of the player.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Method that will check if the player still has a ship alive.
     * @return It will return true if so, otherwise returns false.
     */
    public boolean status() {
        int count = 0;
        for (Ship ship : getShips()) {
            if (ship.isSunk()) {
                count++;
            }
        }
        return count != 5;
    }

    /**
     * Method that will return the number of attacks.
     * @return It will return an integer with the number of attacks.
     */
    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }
}