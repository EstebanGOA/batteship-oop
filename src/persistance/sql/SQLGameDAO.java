package persistance.sql;

import business.UserManager;
import business.entities.User;
import persistance.GameDAO;
import business.entities.Game;

public class SQLGameDAO implements GameDAO {

    private SQLConnector sqlConnector;

    public SQLGameDAO() {
        this.sqlConnector = SQLConnector.getInstance();
    }

    private UserManager userManager;

    public SQLGameDAO(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void addGame(Game game) {
        String query = "INSERT INTO Game(player_id, number_of_attacks, win, game_saved) VALUES (" +
                game.getPlayerId() + ", " +
                game.getNumberOfAttacks() + ", " +
                game.isWin() + ", '" +
                game.getPath() +
                "');";

        sqlConnector.insertQuery(query);

    }

    @Override
    public void addFinishedGame(boolean isWinner, int numberOfAttacks) {
        User user = userManager.getUser();
        Game game = new Game(user.getId(), numberOfAttacks, isWinner, null);
        addGame(game);
    }
}
