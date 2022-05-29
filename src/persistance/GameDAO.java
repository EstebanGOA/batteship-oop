package persistance;

import persistance.sql.Game;

/**
 * Interface GameDAO.
 * This interface is used to manage all the aspects regarding the database and the game.
 */
public interface GameDAO {

    /**
     * Function that adds a game in the database.
     * @param game The game we want to add.
     */
    void addGame(Game game);

    /**
     * Function that adds a finished game in the database.
     * @param isWinner A boolean regarding if the player won.
     * @param numberOfAttacks An integer with the number of attacks of the player.
     */
    void addFinishedGame(boolean isWinner, int numberOfAttacks);

}
