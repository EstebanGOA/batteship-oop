package business.entities;

import business.GameManager;

public class Timer implements Runnable {

    private final int DELAY = 1000;
    private int seconds;
    private int minutes;
    private GameManager gameManager;
    private boolean start;

    public Timer (GameManager gameManager) {
        seconds = 0;
        minutes = 0;
        start = false;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        while (start) {
            try {
                Thread.sleep(DELAY);
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
                gameManager.updateTimer(generateString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        start = true;
    }

    public void stop() {
        start = false;
    }

    public String generateString () {
        return formatTime(minutes) + ":" + formatTime(seconds);
    }

    private String formatTime (int n) {
        return (n < 10) ? "0" + n : String.valueOf(n);
    }

}
