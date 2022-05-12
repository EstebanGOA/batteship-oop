package presentation.views;

public class Cell extends JImagePanel {

    private final int x;
    private final int y;
    private boolean isOccupied;

    public Cell (int x, int y, String img) {
        super(img);
        this.x = x;
        this.y = y;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int[] getCoordinates() {
        return new int[]{x, y};
    }

}
