package persistance.sql;

import business.UserManager;
import business.entities.User;
import persistance.GameDAO;

/**
 * Class SQLGameDAO that implements GameDAO.
 * This class is responsible to communicate with the database, on all the matters regarding the game.
 */
public class SQLGameDAO implements GameDAO {

    private UserManager userManager;

    /**
     * Constructor of SQLGameDAO.
     * @param userManager A UserManager to get the information related with the user that played the game.
     */
    public SQLGameDAO(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Function that adds a game in the database.
     * @param game The finished game we are saving in the database.
     */
    @Override
    public void addGame(Game game) {
        String query = "INSERT INTO Game(player_id, number_of_attacks, win  ) VALUES (" +
                game.getPlayerId() + ", " +
                game.getNumberOfAttacks() + ", " +
                game.isWin() + ");";

        SQLConnector.getInstance().insertQuery(query);

    }

    /**
     * Function that adds a finished game in the database.
     * @param isWinner A boolean regarding if the player won.
     * @param numberOfAttacks An integer with the number of attacks of the player.
     */
    @Override
    public void addFinishedGame(boolean isWinner, int numberOfAttacks) {
        User user = userManager.getUser();
        Game game = new Game(user.getId(), numberOfAttacks, isWinner, null);
        addGame(game);
    }
}
