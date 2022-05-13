package persistance.sql;

import persistance.GameDAO;
import business.entities.Game;

public class SQLGameDAO implements GameDAO {
    private final SQLConnector sqlConnector;

    public SQLGameDAO(SQLConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
    }

    @Override
    public void addGame(Game game) {
        String query = "INSERT INTO Game(id, player_id, number_of_attacks, win, game_saved) VALUES ('" +
                game.getId() + "', '" +
                game.getPlayerId() + "', '" +
                game.getNumberOfAttacks() + "', '" +
                game.isWin() + "', '" +
                game.getGameSaved() +
                "');";
        sqlConnector.insertQuery(query);
    }
}
