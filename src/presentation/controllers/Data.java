package presentation.controllers;

public enum Data {
    CLASS("Cell"),
    END_BATTLE("endBattlebtn");

    private String message;
    Data(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
