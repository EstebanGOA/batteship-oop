package business.entities;

import netscape.javascript.JSObject;

public class Game {
    private int id;
    private int playerId;
    private int numberOfAttacks;
    private boolean win;
    private JSObject gameSaved;

    public Game(int id, int playerId, int numberOfAttacks, boolean win, JSObject gameSaved) {
        this.id = id;
        this.playerId = playerId;
        this.numberOfAttacks = numberOfAttacks;
        this.win = win;
        this.gameSaved = gameSaved;
    }

    public JSObject getGameSaved() { return gameSaved; }

    public int getId() {
        return id;
    }

    public int getPlayerId() { return playerId; }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public boolean isWin() {
        return win;
    }
}
