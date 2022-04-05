package model.dao.sql;

import model.dao.GameDAO;
import model.entity.Game;

public class SQLGameDAO implements GameDAO {

    @Override
    public void addGame(Game game) {
        String query = "INSERT INTO Game(id, player_id, number_of_attacks, win, game_saved) VALUES ('" +
                game.getId() + "', '" +
                game.getPlayerId() + "', '" +
                game.getNumberOfAttacks() + "', '" +
                game.isWin() + "', '" +
                game.getGameSaved() +
                "');";

        SQLConnector.getInstance().insertQuery(query);
    }
}
