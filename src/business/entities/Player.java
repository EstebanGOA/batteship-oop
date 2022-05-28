package business.entities;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase abstracta con las variables comunes entre los jugadores.
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
     * Constructor de Player.
     * @param board Board donde estarán los barcos del jugador.
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
     * Devuelve el tablero del jugador.
     * @return Board donde tiene colocado los barcos el jugador.
     */
    public Board getBoard() {
        return board;
    }

    public boolean[][] getAttacked() {
        return attacked;
    }

    public Color getColor() {
        return color;
    }

    public int getDelay() {
        return delay;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     *
     * @return
     */
    public Ship[] getShips() {
        return ships;
    }

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

    public boolean attack(Player player, int x, int y) {
        // recharging.set(true);
        return player.getBoard().sendAttack(this, x, y);
    }

    public boolean isAttackedAlready(int x, int y) {
        if (!attacked[x][y]) {
            attacked[x][y] = true;
            numberOfAttacks++;
            return false;
        } else {
            return true;
        }
    }

    public void setRecharging(boolean recharging) {
        this.recharging = recharging;
    }

    public boolean isRecharging() {
        return recharging;
    }

    public boolean isAlive() {
        return alive;
    }

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
