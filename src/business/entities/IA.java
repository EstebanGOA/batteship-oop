package business.entities;

import business.GameManager;

import java.util.ArrayList;
import java.util.Arrays;

public class IA extends Player {

    private GameManager gameManager;
    private int checkHit;
    private int[] coords = new int[2];
    private int[] coordsAux = new int[2];
    private boolean[][] hits = new boolean[15][15];
    private boolean orientation;

    public IA(Board board, GameManager gameManager) {
        super(board);
        this.gameManager = gameManager;
        for (boolean[] bol : hits ) {
            Arrays.fill(bol, false);
        }
        orientation = false;
        checkHit = 0;
    }

    private boolean isInsideBoard(int[] cords) {
        return cords[1] < 15 && cords[1] >= 0 && cords[0] < 15 && cords[0] >= 0;
    }

    private int[] generateAttack() {

        if (checkHit == 0) {
            do {
                coords[0] = (int) (Math.random() * 15);
                coords[1] = (int) (Math.random() * 15);
            } while (hits[coords[1]][coords[0]]);
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
        hits[coords[1]][coords[0]] = true;

        return coords;
    }

    @Override
    public void run() {
        try {
            while(isAlive()) {
                boolean check = false;
                Thread.sleep(1000);
                int[] coords = generateAttack();
                ArrayList<Player> players = gameManager.getPlayers();
                for (Player objective : players) {
                    if (!objective.equals(this)) {
                        if(this.attack(objective, coords[0], coords[1])) {
                            check = true;
                        }
                   }
                }
                this.setCheckHit(check);
                gameManager.updateGame();

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
