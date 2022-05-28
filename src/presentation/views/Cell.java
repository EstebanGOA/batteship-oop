package presentation.views;

public class Cell extends JImagePanel {

    private final int x;
    private final int y;

    /**
     *
     * Constructor of the cell class.
     *
     * @param x x coordinate of the cell.
     * @param y y coordinate of the cell.
     * @param img image of the cell.
     *
     */

    public Cell (int x, int y, SpritePath img) {
        super(img);
        this.x = x;
        this.y = y;
    }

    /**
     *
     * Method to return the coordinates of the cell.
     *
     * @return the coordinates of the cell.
     *
     */

    public int[] getCoordinates() {
        return new int[]{x, y};
    }
}
