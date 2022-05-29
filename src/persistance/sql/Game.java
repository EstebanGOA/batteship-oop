package persistance.sql;

/**
 * Class Game.
 * This class stores all the data related to the game.
 */
public class Game {

    private final int playerId;
    private final int numberOfAttacks;
    private final boolean win;


    /**
     * Constructor of Game.
     * @param playerId The player id.
     * @param numberOfAttacks The number of attacks.
     * @param win A boolean with the outcome of the game.
     */
    public Game(int playerId, int numberOfAttacks, boolean win, String path) {
        this.playerId = playerId;
        this.numberOfAttacks = numberOfAttacks;
        this.win = win;
    }

    /**
     * Function that gets a player id.
     * @return Returns an integer with the id.
     */
    public int getPlayerId() { return playerId; }

    /**
     * Function that gets the number of attacks.
     * @return Returns an integer with the number of attacks.
     */
    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    /**
     * Function that gets if the player won.
     * @return Returns a boolean with the outcome of the game.
     */
    public boolean isWin() {
        return win;
    }
}
