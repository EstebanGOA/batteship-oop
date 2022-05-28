package business.entities;

import business.GameManager;

import java.awt.*;
import java.util.ArrayList;

public class IA extends Player implements Runnable {

    private GameManager gameManager;

    /* Auxiliar variables to manage IA intelligence */
    /* Declared volatile so, they are not stored in cache */
    private volatile int checkHit;
    private volatile int[] coords = new int[2];
    private volatile int[] coordsAux = new int[2];
    private volatile boolean orientation;

    public IA(Board board, Color color, GameManager gameManager, int delay) {
        super(board, color, delay);
        this.gameManager = gameManager;
        orientation = false;
        checkHit = 0;
    }

    /**
     * Constructor of IA.
     *
     * Mostly used when  reading a file to load the player status.
     *
     * @param alive Boolean with the player status .
     * @param board Board where the ships are placed.
     * @param gameManager GameManager which controls the game status.
     */
    public IA(boolean alive, Board board, boolean[][]attacked, Color color, GameManager gameManager, int delay) {
        super(board, false, alive, 0, attacked, color, delay);
        this.gameManager = gameManager;
    }



    private boolean isInsideBoard(int[] cords) {
        return cords[1] < 15 && cords[1] >= 0 && cords[0] < 15 && cords[0] >= 0;
    }

    private int[] generateAttack() {
        if (checkHit == 0) {
            do {
                coords[0] = (int) (Math.random() * 15);
                coords[1] = (int) (Math.random() * 15);
            } while (getAttacked()[coords[1]][coords[0]]);
            coordsAux = new int[] {coords[0], coords[1]};
        } else if (checkHit == 1) {
            coords[1]++;
            if (!isInsideBoard(coords)) {
                coords = coordsAux.clone();
                checkHit = 2;
            }
        } else if ( checkHit == 2) {
            coords[1]--;
            if (!isInsideBoard(coords)) {
                coords = coordsAux.clone();
                checkHit = 3;
            }
        } else if (checkHit == 3) {
            coords[0]++;
            if (!isInsideBoard(coords)) {
                coords = coordsAux.clone();
                checkHit = 4;
            }
        } else if (checkHit == 4) {
            coords[0]--;
            if (!isInsideBoard(coords)) {
                coords = coordsAux.clone();
                checkHit = 0;
            }
        }
        getAttacked()[coords[1]][coords[0]] = true;

        return coords;
    }

    @Override
    public void run() {

        while(!isStop() && isAlive()) {

            try {
                boolean check = false;
                Thread.sleep(getDelay());
                int[] coords = generateAttack();
                ArrayList<Player> players = gameManager.getPlayers();
                for (Player objective : players) {
                    if (!objective.equals(this)) {
                        if (this.attack(objective, coords[0], coords[1])) {
                            check = true;
                        }
                    }
                }
                this.setCheckHit(check);
                gameManager.updateGame(this);
                System.out.println("IA");

            } catch (InterruptedException e) {
                /* We catch the interrupted exception but don't show any kind of message. */
                e.printStackTrace();
            }
        }

    }

    public void setCheckHit(boolean hit) {

        switch (checkHit) {
            case 0:
                this.checkHit = (hit) ? 1 : 0;
                break;
            case 1:
                if (!hit) {
                    checkHit = 2;
                    coords = coordsAux.clone();
                } else {
                    orientation = true;
                }
                break;
            case 2:
                if (!hit) {
                    checkHit = 3;
                    coords = coordsAux.clone();
                    // Check if the ship was vertical.
                    if (orientation) {
                        checkHit = 0;
                    }
                } else {
                    orientation = true;
                }
                break;
            case 3:
                if (!hit) {
                    checkHit = 4;
                    coords = coordsAux.clone();
                }
                break;
            case 4:
                if(!hit) {
                    checkHit = 0;
                }
                break;
        }
    }
}
