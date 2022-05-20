package business;

import business.entities.*;
import persistance.GameDAO;
import persistance.sql.SQLGameDAO;
import presentation.controllers.GameController;

import java.util.ArrayList;

public class GameManager {

    private GameDAO gameDao;
    private ArrayList<Player> players;
    private GameController gameController;
    private Timer timer;

    public GameManager() {
        this.gameDao = new SQLGameDAO();
        this.players = new ArrayList<>();
    }

    /**
     * This function creates a new player nad assign to him a board.
     */
    public void createPlayer() {
        Board board = new Board();
        Player player = new Human(board);
        players.add(player);
    }

    public void updateTimer(String time) {
        gameController.updateTimer(time);
    }

    public void stopTimer () {
        this.timer.stop();
    }

    public void startTimer() {
        this.timer = new Timer(this);
        Thread thread = new Thread(timer);
        this.timer.resume();
        thread.start();
    }

    public Board insertShip(int[] cords, String shipSelected, String orientation) {

        if (players.isEmpty()) {
            createPlayer();
        }

        Player player = players.get(0);

        return player.insertShip(cords, shipSelected, orientation);

    }

    public boolean isSetupStageReady() {

        if (players.isEmpty()) {
            return false;
        }

        return players.get(0).allShipPlaced();

    }

    public void createIA(int numberOfEnemies) {
        String[] shipSelected = {"Boat", "Submarine1", "Submarine2", "Destructor", "Aircraft"};
        int X = 0;
        int Y = 0;
        int[] cords = {X, Y};
        int orient = 0;
        String[] orientation = {"horizontal", "vertical"};
        for(int currentEnemy = 0; currentEnemy < numberOfEnemies; currentEnemy++) {
            Board board = new Board();
            Player IA = new IA(board, players);

            for(int currentBoat = 0; currentBoat < 5; currentBoat++) {
                boolean insert = false;
                while (!insert) {
                    X = (int) (Math.random() * 15);
                    Y = (int) (Math.random() * 15);
                    orient = (int) (Math.random() * 2);
                    cords[0] = X;
                    cords[1] = Y;
                    if (IA.insertShip(cords, shipSelected[currentBoat], orientation[orient]) != null) {
                        insert = true;
                    }
                }
            }
            players.add(IA);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void attack(Player player, int x, int y) {

        for (Player objective : players) {
            if (!objective.equals(player)) {
                player.attack(objective, x, y);
            }
        }

    }

    public void asigneController(GameController gameController) {
        this.gameController = gameController;
    }

}
