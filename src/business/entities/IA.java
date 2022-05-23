package business.entities;

import business.GameManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class IA extends Player {

    private GameManager gameManager;

    public IA(Board board, GameManager gameManager) {
        super(board);
        this.gameManager = gameManager;
    }

    private int[] generateAttack() {
        int[] coords = new int[2];
        coords[0] = (int) (Math.random() * 15);
        coords[1] = (int) (Math.random() * 15);
        return coords;
    }

    @Override
    public void run() {
        try {
            while(isAlive()) {

                Thread.sleep(1000);
                int[] coords = generateAttack();
                ArrayList<Player> players = gameManager.getPlayers();
                for (Player objective : players) {
                    if (!objective.equals(this)) {
                        this.attack(objective, coords[0], coords[1]);
                    }
                }
                gameManager.updateGame();

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
