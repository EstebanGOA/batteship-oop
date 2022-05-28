package business.entities;

import business.GameManager;

public class Timer implements Runnable {

    private final int DELAY = 1000;
    private int seconds;
    private int minutes;
    private GameManager gameManager;

    public Timer (GameManager gameManager) {
        seconds = 0;
        minutes = 0;
        this.gameManager = gameManager;
    }

    public Timer(int minutes, int seconds, GameManager gameManager) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
            try {
                while (true) {
                    Thread.sleep(DELAY);
                    seconds++;
                    if (seconds == 60) {
                        minutes++;
                        seconds = 0;
                    }
                    gameManager.updateTimer(generateString());
                }
            } catch (InterruptedException e) {
                /* We catch the interrupted exception but don't show any kind of message. */
            }
    }

    public String generateString () {
        return formatTime(minutes) + ":" + formatTime(seconds);
    }

    private String formatTime (int n) {
        return (n < 10) ? "0" + n : String.valueOf(n);
    }

}
