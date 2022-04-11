package model;

import model.dao.GameDAO;
import model.dao.sql.SQLGameDAO;
import model.entity.Game;

public class GameManager {
    private GameDAO gameDao;

    public GameManager() {
        gameDao = new SQLGameDAO();
    }

    public void addGame(Game game) { gameDao.addGame(game); }
}
