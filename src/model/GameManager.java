package model;

import model.dao.GameDAO;
import model.entity.Game;

public class GameManager {
    private GameDAO gameDao;

    public GameManager(GameDAO gameDao) {
        this.gameDao = gameDao;
    }

    public void addGame(Game game) { gameDao.addGame(game); }
}
