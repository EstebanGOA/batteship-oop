package business.entities;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Clase abstracta con las variables comunes entre los jugadores.
 */
abstract public class Player implements Runnable {

    private Board board;

    private Ship[] ships;

    private AtomicBoolean recharging;
    private AtomicBoolean isAlive;

    /**
     * Constructor de Player.
     * @param board Board donde estarán los barcos del jugador.
     */
    public Player(Board board) {
        this.board = board;
        this.ships = new Ship[5];
        this.recharging = new AtomicBoolean(false);
        this.isAlive = new AtomicBoolean(true);
    }

    /**
     * Devuelve el tablero del jugador.
     * @return Board donde tiene colocado los barcos el jugador.
     */
    public Board getBoard() {
        return board;
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

    public void attack(Player player, int x, int y) {
        player.getBoard().sendAttack(x, y);
        recharging.set(true);
    }

    public void setRecharging(boolean recharging) {
        this.recharging.set(recharging);
    }

    public boolean isRecharging() {
        return recharging.get();
    }

    public boolean isAlive() {
        return isAlive.get();
    }

    public void setAlive(boolean alive) {
        isAlive.set(alive);
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
}
