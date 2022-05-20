package business.entities;

import java.util.ArrayList;

public class IA extends Player implements Runnable {

    private ArrayList<Player> players;

    public IA(Board board, ArrayList<Player> players) {
        super(board);
        this.players = players;
    }

    private int[] generateAttack() {
     return null;
    }

    @Override
    public void run() {

        for (Player objective : players) {
            if (!objective.equals(this)) {
                this.attack(objective, x, y);
            }
        }

    }

}
