package business;

import business.entities.*;
import persistance.ConfigDAO;
import persistance.GameDAO;
import persistance.GameJSON;
import persistance.sql.SQLGameDAO;
import presentation.controllers.GameController;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class GameManager
 * This class manages all the logic related with the game.
 */
public class GameManager {

    private ConfigDAO configDAO;
    private String gameName;
    private GameJSON gameJSON;

    private GameController gameController;
    private GameDAO gameDao;

    private ArrayList<Player> players;
    private ArrayList<Thread> threads;

    private Timer timer;
    private Thread timerThread;

    /**
     * Constructor of GameManager.
     * @param sqlGameDAO A SQLGameDAO to persist the information or read it.
     */
    public GameManager(SQLGameDAO sqlGameDAO) {
        this.gameDao = sqlGameDAO;
        this.gameJSON = new GameJSON(this);
        this.configDAO = new ConfigDAO();
        this.players = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    /**
     * This function creates a new player nad assign to him a board.
     */
    public void createPlayer() {
        Board board = new Board();
        int delay = configDAO.getDelay();
        Player player = new Human(board, delay, this);
        players.add(player);
    }

    /**
     * Function that updates the timer.
     * @param time A string with the time.
     */
    public void updateTimer(String time) {
        gameController.updateTimer(time);
    }

    /**
     * Function that stops the game, all the threats are interrupt.
     */
    public void stopGame() {

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setStop(true);
        }
        timer.setStop(true);
    }

    /**
     * Function that starts the timer, if the timer is null it creates one.
     */
    public void startTimer() {
        if (timer == null) {
            timer = new Timer(this);
        }
        timer.setStop(false);
        timerThread = new Thread(timer);
        timerThread.start();
    }

    /**
     * Function that set the timer.
     * @param timer The new timer.
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
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

    /**
     * Function that checks if the setupStage is ready, and it can proceed to the gameStage.
     * @return A boolean regarding the state of the setup stage.
     */
    public boolean isSetupStageReady() {

        if (players.isEmpty()) {
            return false;
        }

        return players.get(0).allShipPlaced();

    }

    /**
     * Function that starts the game.
     * This function creates threads for all the players and starts them alongside the timer .
     */
    public void startGame() {
        threads = new ArrayList<>();
        for (Player p : players) {
            p.setStop(false);
            Thread thread = new Thread(p);
            threads.add(thread);
            thread.start();
        }
        /* In some cases this call is redundant but NOT ALWAYS! */
        startTimer();
    }

    /**
     * Function that creates the IA.
     * This function creates all the IA necessary depending on the number of enemies the player has chosen.
     * For each IA the ships will be randomly created.
     * @param numberOfEnemies An integer of the number of enemies.
     */
    public void createIA(int numberOfEnemies) {
        String[] shipSelected = {"Boat", "Submarine1", "Submarine2", "Destructor", "Aircraft"};
        int x = 0;
        int y = 0;
        int orient = 0;
        String[] orientation = {"horizontal", "vertical"};
        Color[] colors = new Color[]{Color.RED, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE };
        for (int currentEnemy = 0; currentEnemy < numberOfEnemies; currentEnemy++) {
            Board board = new Board();
            Player IA = new IA(board, colors[currentEnemy], this, configDAO.getDelay());

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

    /**
     * Function that loads the game.
     * @param gameName A String with the name we want to load.
     * @return Returns the array length of the players that are IA.
     */
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

    /**
     * Function used to attack, with this function the user attacks the IA.
     * @param player The player who attacks.
     * @param x An integer of the x coordinate.
     * @param y An integer of the y coordinate.
     */
    public void attack(Player player, int x, int y) {
        if (!player.isAttackedAlready(x, y)) {

            for (Player objective : players) {
                if (!objective.equals(player)) {
                    player.attack(objective, x, y);
                }
            }

            player.setRecharging(true);
            updatePhase("Recharging");
            updateGame(player);

        }
    }

    /**
     * Function that update the phase of the game.
     * @param status A String with the status.
     */
    public void updatePhase(String status) {
        gameController.updatePhase(status);
    }

    /**
     * Function that updates the game
     */
    public void updateGame(Player player)  {

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


        if (count == players.size()-1 && player.equals(players.get(winner))) {

            stopGame();
            saveGame(winner);
            gameController.returnMenu(winner);
            reset();

            /* In some cases we will be playing a saved game so, if gameName is not null it means that the game was loaded so, we need to delete the file
            * associated with it. */
            if (gameName != null) {
                gameJSON.deleteFile(gameName);
            }

        }

    }

    /**
     * This function assigns the GameController.
     * @param gameController The GameController that is assigned.
     */
    public void assignController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Function that persists the finished game in the database.
     * @param winner The player that has won the game.
     */
    private void saveGame(int winner) {
        Player p = players.get(0);
        boolean isWinner = winner == 0;
        gameDao.addFinishedGame(isWinner, p.getNumberOfAttacks());
    }

    /**
     * Function that persists an unfinished game.
     * @param filename A string with the name of the game.
     */
    public void saveUnfinishedGame(String filename) {
        try {
            gameJSON.create(filename);
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            gameJSON.addUnfinishedGame(timer, date.format(LocalDateTime.now()), players, filename);
            reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function that resets the game.
     * This includes the players, threads and the timer.
     */
    public void reset() {
        players = new ArrayList<>();
        threads = new ArrayList<>();
        timer = new Timer(this);
    }

    /**
     * Function that gets all the players.
     * @return Returns an array of all the players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }



    /**
     * Functions that get all files from the directory "saves"
     * @return It will return an array with all the Files inside the directory.
     */
    public File[] getFiles() {
        return gameJSON.getFiles();
    }
}
