package persistance;


import business.GameManager;
import business.entities.*;
import com.google.gson.*;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GameJSON {

    private final Gson gson;
    private GameManager gameManager;
    private ConfigDAO configDAO;
    private String PATH = "saves/";

    public GameJSON(GameManager gameManager) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.gameManager = gameManager;
        this.configDAO = new ConfigDAO();
    }

    public void create(String filename) throws IOException {
        Path p = getPath(filename);
        //Si no existe lo crea sino lo sobrescribe.
        if (!Files.exists(p)) {
            Files.createFile(p);
        }
    }

    public Path getPath(String filename) {
        String format = PATH + filename + ".json";
        return Paths.get(format);
    }

    public boolean exist(String name) {
        Path path = getPath(name);
        return Files.exists(path);
    }

    public File[] getFiles() {
        File directory = new File("saves/");
        File[] files = directory.listFiles();
        return files;
    }
    public void addUnfinishedGame(Timer timer, String date, ArrayList<Player> players, String filename) throws IOException {

        ArrayList<Player> play = players;
        FileWriter writer = new FileWriter(PATH + filename + ".json");
        JsonObject jsonObjectGame = new JsonObject();

        jsonObjectGame.addProperty("date", date);
        jsonObjectGame.addProperty("time", timer.generateString());

        JsonArray jsonArrayPlayers = new JsonArray();
        for (int i = 0; i < play.size(); i++) {
            JsonArray jsonArrayShips = new JsonArray();
            JsonObject jsonObjectPlayer = new JsonObject();
            Player player = play.get(i);

            if (i == 0) {
                jsonObjectPlayer.addProperty("recharging", player.isRecharging());
                jsonObjectPlayer.addProperty("number_attacks",  player.getNumberOfAttacks());
            }
            jsonObjectPlayer.addProperty("is_alive", player.isAlive());

            Ship[] ships = player.getShips();
            for (int j = 0; j < ships.length; j++) {
                JsonObject jsonObjectShip = new JsonObject();

                jsonObjectShip.addProperty("type", ships[j].getSize());

                int[] position = ships[j].getPosition();
                JsonArray jsonArrayPosition = new JsonArray();
                jsonArrayPosition.add(position[0]);
                jsonArrayPosition.add(position[1]);

                jsonObjectShip.add("initial_position", jsonArrayPosition);

                jsonObjectShip.addProperty("orientation", ships[j].getOrientation());

                jsonArrayShips.add(jsonObjectShip);
            }
            jsonObjectPlayer.add("ships", jsonArrayShips);

            Board board = player.getBoard();
            JsonArray jsonArrayCells = new JsonArray();

            boolean[][] attacked = player.getAttacked();
            JsonArray jsonArrayAttacked = new JsonArray();

            int x = 0, y = 0;
            while (x < 15) {
                JsonArray jsonArrayCell = new JsonArray();
                JsonArray jsonArrayAttack = new JsonArray();
                while (y < 15) {
                    Tile[][] tiles = board.getTiles();

                    JsonObject jsonObjectTile = new JsonObject();
                    jsonObjectTile.addProperty("tileType", tiles[x][y].getTileType().getValue());
                    Color color = tiles[x][y].getColor();
                    jsonObjectTile.addProperty("color", String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
                    jsonArrayCell.add(jsonObjectTile);
                    JsonObject jsonObjectAttack = new JsonObject();
                    jsonObjectAttack.addProperty("isAttack", attacked[x][y]);
                    jsonArrayAttack.add(jsonObjectAttack);

                    y++;
                }
                jsonArrayCells.add(jsonArrayCell);
                jsonArrayAttacked.add(jsonArrayAttack);

                y = 0;
                x++;
            }

            jsonObjectPlayer.add("board", jsonArrayCells);
            jsonObjectPlayer.add("attacked", jsonArrayAttacked);

            jsonArrayPlayers.add(jsonObjectPlayer);
        }
        jsonObjectGame.add("players", jsonArrayPlayers);

        gson.toJson(jsonObjectGame, writer);
        writer.close();
    }

    public void deleteFile(String filename) {
        if (exist(filename)) {
            new File(PATH + filename + ".json").delete();
        }
    }

    public ArrayList<Player> loadGame(String filename) throws IOException {

        ArrayList<Player> players = new ArrayList<>();
        Path path = getPath(filename);

        if (Files.exists(path)) {
            JsonObject jsonObjectGame = JsonParser.parseString(Files.readString(path)).getAsJsonObject();
            String[] time = jsonObjectGame.get("time").getAsString().split(":");
            int minutes = Integer.parseInt(time[0]);
            int seconds = Integer.parseInt(time[1]);
            Timer timer = new Timer(minutes, seconds, gameManager);
            gameManager.setTimer(timer);

            JsonArray jsonArrayPlayers = jsonObjectGame.getAsJsonArray("players").getAsJsonArray();
            for (int i = 0; i < jsonArrayPlayers.size(); i++) {
                players.add(playerFromJson(jsonArrayPlayers.get(i).getAsJsonObject(), i));
            }
        }
        return players;
    }


    private Player playerFromJson(JsonObject player, int i) {

        // Auxiliar Player where all data is going to be stored.
        Player p = null;
        Color[] colors = new Color[]{Color.RED, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE };

        JsonArray jsonArrayBoard = player.get("board").getAsJsonArray();

        Board board = new Board();
        Tile[][] tiles = board.getTiles();
        boolean[][] attacked = new boolean[15][15];

        boolean alive = player.get("is_alive").getAsBoolean();
        int delay = configDAO.getDelay();

        if (i == 0) {
            //Cargamos al Human
            boolean recharging = player.get("recharging").getAsBoolean();
            int numberAttacks = player.get("number_attacks").getAsInt();
            p = new Human(alive, recharging, numberAttacks, board, attacked, gameManager, delay);

        } else {
            //Cargamos al IA
            p = new IA(alive, board, attacked, colors[i-1], gameManager, delay);
        }

        JsonArray jsonArrayShips = player.get("ships").getAsJsonArray();

        boolean flag = false;
        for (int j = 0; j < jsonArrayShips.size(); j++) {
            JsonObject jsonObjectShip = jsonArrayShips.get(j).getAsJsonObject();
            int type = jsonObjectShip.get("type").getAsInt();

            JsonArray jsonArrayPosition = jsonObjectShip.get("initial_position").getAsJsonArray();
            int[] initialPosition = new int[2];
            initialPosition[0] = jsonArrayPosition.get(0).getAsInt();
            initialPosition[1] = jsonArrayPosition.get(1).getAsInt();

            String orientation = jsonObjectShip.get("orientation").getAsString();

            switch (type) {
                case 2:
                    p.insertShip(initialPosition, "Boat" ,orientation);
                    break;
                case 3:
                    if (flag) {
                        p.insertShip(initialPosition, "Submarine2" ,orientation);
                    } else {
                        p.insertShip(initialPosition, "Submarine1" ,orientation);
                        flag = true;
                    }
                    break;
                case 4:
                    p.insertShip(initialPosition, "Destructor" ,orientation);
                    break;
                case 5:
                    p.insertShip(initialPosition,"Aircraft" ,orientation);
                    break;
            }
         }

        JsonArray jsonArrayAttacked = player.get("attacked").getAsJsonArray();

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {

                JsonObject jsonObjectAttack = jsonArrayAttacked.get(x).getAsJsonArray().get(y).getAsJsonObject();
                boolean attack = jsonObjectAttack.get("isAttack").getAsBoolean();
                attacked[x][y] = attack;

                JsonObject jsonObjectTile = jsonArrayBoard.get(x).getAsJsonArray().get(y).getAsJsonObject();
                int tileType = jsonObjectTile.get("tileType").getAsInt();
                String hexColor = jsonObjectTile.get("color").getAsString();
                Color color = Color.decode(hexColor);

                switch (tileType) {
                    case 0:
                        tiles[x][y].setTileType(TileType.WATER);
                        tiles[x][y].changeColor(color);
                        break;
                    case 1:
                        tiles[x][y].setTileType(TileType.SHIP);
                        tiles[x][y].changeColor(color);
                        break;
                    case 2:
                        tiles[x][y].setTileType(TileType.HIT);
                        tiles[x][y].changeColor(color);
                        break;
                    case 4:
                        tiles[x][y].setTileType(TileType.MISS);
                        tiles[x][y].changeColor(color);
                        break;
                }
            }
        }

        return p;
    }

}