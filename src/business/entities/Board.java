package business.entities;

import presentation.views.JPopup;

public class Board {

    private Tile[][] tiles;

    public Board() {
        this.tiles = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.tiles[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public boolean placeShip(Ship ship) {

        int boardStart = 0;
        int boardLimit = 16;
        int x = ship.getPosition()[0];
        int y = ship.getPosition()[1];
        int shipLimit = tiles.length - ship.getSize();
        boolean status = true;

        if (ship.getOrientation().equals("horizontal")) {

            if (x >= boardStart && x <= shipLimit && y >= boardStart && x < boardLimit) {

                for (int i = 0; i < ship.getSize(); i++) {
                    if (tiles[y][x+i].getTileType() != TileType.WATER) {
                        status = false;
                        break;
                    }
                }

                if (status) {
                    for (int i = 0; i < ship.getSize(); i++) {
                        tiles[y][x+i] = new ShipSegment();
                        ship.addSegment((ShipSegment) tiles[y][x+i]);
                        tiles[y][x+i].setTileType(TileType.SHIP);
                    }
                }

            } else {
                status = false;
            }

        } else if (ship.getOrientation().equals("vertical")) {

            if (x >= boardStart && x < boardLimit && y >= boardStart && y <= shipLimit) {

                for (int i = 0; i < ship.getSize(); i++) {
                    if (tiles[y+i][x].getTileType() != TileType.WATER) {
                        status = false;
                        break;
                    }
                }

                if (status) {
                    for (int i = 0; i < ship.getSize(); i++) {
                        tiles[y+i][x] = new ShipSegment();
                        ship.addSegment((ShipSegment) tiles[y+i][x]);
                        tiles[y+i][x].setTileType(TileType.SHIP);
                    }
                }

            } else {
                status = false;
            }

        } else {
            status = false;
        }

        return status;

    }

    public void remove(Ship ship) {

        int x = ship.getPosition()[0];
        int y = ship.getPosition()[1];

        if (ship.getOrientation().equals("horizontal")) {
            for (int i = 0; i < ship.getSize(); i++) {
                    tiles[y][x+i] = new Tile();
            }
        } else {
            for (int i = 0; i < ship.getSize(); i++) {
                tiles[y+i][x] = new Tile();
            }
        }

    }
}
