package business;

import business.entities.Game;
import persistance.GameDAO;
import persistance.sql.SQLGameDAO;

public class GameManager {
    private final GameDAO gameDAO;

    public GameManager(SQLGameDAO sqlGameDAO) {
        this.gameDAO = sqlGameDAO;
    }

    public void addGame(Game game) {
        gameDAO.addGame(game);
    }
}
