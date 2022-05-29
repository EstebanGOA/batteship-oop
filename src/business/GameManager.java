package business;

import business.entities.*;
import persistance.GameDAO;
import persistance.GameJSON;
import persistance.sql.SQLGameDAO;
import presentation.controllers.GameController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameManager {

    private String gameName;
    private GameJSON gameJSON;

    private GameController gameController;
    private GameDAO gameDao;

    private ArrayList<Player> players;
    private ArrayList<Thread> threads;

    private Timer timer;
    private Thread timerThread;

    public GameManager(SQLGameDAO sqlGameDAO) {
        this.gameDao = sqlGameDAO;
        this.gameJSON = new GameJSON(this);
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

    public void stopGame() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        timerThread.interrupt();
    }

    public void startTimer() {
        if (timer == null) {
            timer = new Timer(this);
        }
        timerThread = new Thread(timer);
        timerThread.start();
    }

    /**
     * Method that will insert a ship inside the player (Human) board.
     *
     * NOTE: This function only will be called from controller when user inputs data through the window that's why we get
     * the first player from the array always.
     *
     * @param cords Selected coordinates.
     * @param shipSelected Ship selected.
     * @param orientation Orientation of the ship.
     * @return Board with the new updated data, if the ship cannot be placed board will be null.
     */
    public Board insertShip(int[] cords, String shipSelected, String orientation) {

        if (players.isEmpty()) {
            createPlayer();
        }

        /* We always pick the first player because this function will be only called when the user clics on the view */
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
        /* In some cases this call is redundant but NOT ALWAYS! */
        startTimer();
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

    public int loadGame(String gameName){
        try {
            reset();
            this.players = gameJSON.loadGame(gameName);
            this.gameName = gameName;
            /* It will return the array length because we need it to paint the boards for the enemies, so we make the size minus 1 */
            return players.size() - 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            reset();

            /* In some cases we will be playing a saved game so, if gameName is not null it means that the game was loaded so, we need to delete the file
            * associated with it. */
            if (gameName != null) {
                gameJSON.deleteFile(gameName);
            }
        }

    }

    public void assignController(GameController gameController) {
        this.gameController = gameController;
    }

    private void saveGame(Player winner) {
        boolean isWinner = winner instanceof Human;
        gameDao.addFinishedGame(isWinner, winner.getNumberOfAttacks().get());
    }

    public void saveUnfinishedGame(String gameName) {
        try {
            gameJSON.create(gameName);
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            gameJSON.addUnfinishedGame(timer, date.format(LocalDateTime.now()), players, gameName);
            reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() {
        players = new ArrayList<>();
        threads = new ArrayList<>();
        timer = new Timer(this);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean fileExist(String name) {
        return gameJSON.exist(name);
    }
}
