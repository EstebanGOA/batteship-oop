package business.entities;


public class Game {

    private final int playerId;
    private final int numberOfAttacks;
    private final boolean win;
    private final String path;

    /**
     * Constructor of Game, used to
     * @param playerId
     * @param numberOfAttacks
     * @param win
     */
    public Game(int playerId, int numberOfAttacks, boolean win, String path) {
        this.playerId = playerId;
        this.numberOfAttacks = numberOfAttacks;
        this.win = win;
        this.path = null;
    }

    public int getPlayerId() { return playerId; }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public boolean isWin() {
        return win;
    }
}
