package persistance;

import business.GameManager;
import business.UserManager;
import business.entities.Game;

public interface GameDAO {

    void addGame(Game game);

    void addFinishedGame(boolean isWinner, int numberOfAttacks);

}
