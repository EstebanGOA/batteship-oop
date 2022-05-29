package business.entities;

import business.GameManager;

/**
 * Class Timer that implements Runnable.
 * This class is in charge of counting the time that has passed since the start of the Game.
 */
public class Timer implements Runnable {

    private final int DELAY = 1000;
    private int seconds;
    private int minutes;
    private boolean stop;
    private GameManager gameManager;

    /**
     * Constructor of Timer.
     * @param gameManager GameManager that controls the logic of the game.
     */
    public Timer (GameManager gameManager) {
        seconds = 0;
        minutes = 0;
        this.gameManager = gameManager;
        stop = false;
    }

    /**
     * Constructor of Timer.
     * @param minutes An integer with the minutes.
     * @param seconds An integer with the seconds.
     * @param gameManager The GameManager.
     */
    public Timer(int minutes, int seconds, GameManager gameManager) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.gameManager = gameManager;
        stop = false;
    }

    /**
     * Function that starts running when the game is started and keeps running until the game ends or the user ends the game.
     * While running the game counts the time that has passed since the game started.
     */
    @Override
    public void run() {
        while (!isStop()) {
            try {
                Thread.sleep(DELAY);
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
                gameManager.updateTimer(generateString());

            } catch (InterruptedException e) {
                /* We catch the interrupted exception but don't show any kind of message. */
            }
        }
    }

    /**
     * Function that checks if the timer is stopped.
     * @return Returns a boolean regarding the outcome.
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * Function that sets if the timer is stop.
     * @param stop A boolean that updates the new state of the timer.
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Function that generates a string using the time that has passed since the game started.
     * @return A string with the time.
     */
    public String generateString () {
        return formatTime(minutes) + ":" + formatTime(seconds);
    }

    /**
     * Function that formats the time ensuring that the string is correct.
     * @param n An integer with the minutes or the seconds.
     * @return A string with the correct format.
     */
    private String formatTime (int n) {
        return (n < 10) ? "0" + n : String.valueOf(n);
    }
}
