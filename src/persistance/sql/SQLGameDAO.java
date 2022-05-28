package persistance.sql;

import business.GameManager;
import business.UserManager;
import business.entities.User;
import persistance.GameDAO;
import business.entities.Game;

public class SQLGameDAO implements GameDAO {

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

        SQLConnector.getInstance().insertQuery(query);

    }

    @Override
    public void addFinishedGame(boolean isWinner, int numberOfAttacks) {
        User user = userManager.getUser();
        Game game = new Game(user.getId(), numberOfAttacks, isWinner, null);
        addGame(game);
    }
}
