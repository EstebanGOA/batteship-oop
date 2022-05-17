package presentation.views;

public class Cell extends JImagePanel {

    private final int x;
    private final int y;

    public Cell (int x, int y, String img) {
        super(img);
        this.x = x;
        this.y = y;
    }

    public int[] getCoordinates() {
        return new int[]{x, y};
    }

}
