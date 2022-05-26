package business;

import business.entities.*;
import persistance.GameDAO;
import persistance.SaveGameJSON;
import persistance.sql.SQLGameDAO;
import presentation.controllers.GameController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameManager {
    private String gameName = "Kevin";
    private SaveGameJSON saveGameJSON;
    private GameDAO gameDao;
    private ArrayList<Player> players;
    private GameController gameController;
    private Timer timer;
    private ArrayList<Thread> threads;

    public GameManager(SQLGameDAO sqlGameDAO) throws IOException {
        this.gameDao = sqlGameDAO;
        this.players = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    /**
     * This function creates a new player nad assign to him a board.
     */
    public void createPlayer() {
        Board board = new Board();
        Player player = new Human(board, this);
        players.add(player);
    }

    public void updateTimer(String time) {
        gameController.updateTimer(time);
    }

    public void stopGame() throws IOException {
        this.timer.stop();
        for (Thread thread : threads) {
            thread.interrupt();
        }

        this.saveGameJSON = new SaveGameJSON(gameName);
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        saveGameJSON.addUnfinishedGame(timer, date.format(LocalDateTime.now()), players);
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

    public void startGame() {
        for (Player p : players) {
            Thread thread = new Thread(p);
            threads.add(thread);
            thread.start();
        }
    }

    public void createIA(int numberOfEnemies) {
        String[] shipSelected = {"Boat", "Submarine1", "Submarine2", "Destructor", "Aircraft"};
        int x = 0;
        int y = 0;
        int orient = 0;
        String[] orientation = {"horizontal", "vertical"};
        for (int currentEnemy = 0; currentEnemy < numberOfEnemies; currentEnemy++) {
            Board board = new Board();
            Player IA = new IA(board, this);

            for (int currentBoat = 0; currentBoat < 5; currentBoat++) {
                boolean insert = false;
                while (!insert) {
                    x = (int) (Math.random() * 15);
                    y = (int) (Math.random() * 15);
                    orient = (int) (Math.random() * 2);
                    int[] cords = {x, y};
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

    public void attack(Player player, int x, int y) throws IOException {
        if (player.isAttackedAlready(x, y)) {

            for (Player objective : players) {
                if (!objective.equals(player)) {
                    player.attack(objective, x, y);
                }
            }

            player.setRecharging(true);
            updatePhase("Recharging");
            updateGame();

        }

    }

    public void updatePhase(String status) {
        gameController.updatePhase(status);
    }

    public void updateGame() throws IOException {
        int count = 0, winner = 0;
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (!p.status()) {
                count++;
                p.setAlive(false);
            } else {
                winner = i;
            }
        }

        gameController.updateGame(players);

        if (count == players.size()-1) {
            stopGame();
            Player p = players.get(winner);
            saveGame(p);
            gameController.returnMenu(p);
            players = new ArrayList<>();
            threads = new ArrayList<>();

            //En el caso de haber terminado una partida previamente guardada, esta debe ser eliminada del programa.
            saveGameJSON.deleteFile(gameName);
        }
    }

    public void asigneController(GameController gameController) {
        this.gameController = gameController;
    }

    private void saveGame(Player winner) {
        boolean isWinner = winner instanceof Human;
        gameDao.addFinishedGame(isWinner, winner.getNumberOfAttacks().get());
    }
}
