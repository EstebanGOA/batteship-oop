package business;

import business.entities.*;
import persistance.GameDAO;
import persistance.sql.SQLGameDAO;

import java.util.ArrayList;

public class GameManager {

    private GameDAO gameDao;
    private ArrayList<Player> players;
    private Timer timer;

    public GameManager() {
        this.gameDao = new SQLGameDAO();
        this.players = new ArrayList<>();
        this.timer = new Timer();
    }

    /**
     * This function creates a new player nad assign to him a board.
     */
    public void createPlayer() {
        Board board = new Board();
        Player player = new Human(board);
        players.add(player);
    }


    public Board insertShip(int[] cords, String shipSelected, String orientation) {

        if (players.isEmpty()) {
            createPlayer();
        }

        Player player = players.get(0);

        return player.insertShip(cords, shipSelected, orientation);

    }

    public void addGame(Game game) {
        gameDao.addGame(game);
    }

    public boolean isSetupStageReady() {
        return players.get(0).allShipPlaced();
    }

    public void createIA(int numberOfEnemies) {
        String[] shipSelected = {"Boat", "Submarine1", "Submarine2", "Destructor", "Aircraft"};
        int X = 0;
        int Y = 0;
        int[] cords = {X, Y};
        int orient = 0;
        String[] orientation = {"horitzontal", "vertical"};
        for(int currentEnemy = 0; currentEnemy < numberOfEnemies; currentEnemy++){
            Board board = new Board();
            Player IA = new IA(board);

            for(int currentBoat = 0; currentBoat < 5; currentBoat++){
                X = (int) (Math.random() * 15);
                Y = (int) (Math.random() * 15);
                orient = (int) (Math.random() * 1);
                cords[0] = X;
                cords[1] = Y;
                IA.insertShip(cords, shipSelected[currentBoat], orientation[orient]);
            }
            players.add(IA);
        }
    }

}
