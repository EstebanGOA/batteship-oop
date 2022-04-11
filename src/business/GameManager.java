package business;

import business.entities.Game;
import persistance.GameDAO;
import persistance.sql.SQLGameDAO;

public class GameManager {

    private GameDAO gameDao;

    public GameManager() {
        gameDao = new SQLGameDAO();
    }

    public void addGame(Game game) {
        gameDao.addGame(game);
    }

}
