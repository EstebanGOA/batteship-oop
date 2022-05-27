package persistance;


import business.GameManager;
import business.entities.*;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GameJSON {

    private final Gson gson;
    private GameManager gameManager;

    public GameJSON(GameManager gameManager) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.gameManager = gameManager;
    }

    public void create(String nameGame) throws IOException {
        Path p = getPath(nameGame);
        //Si no existe lo crea sino lo sobrescribe.
        if (!Files.exists(p)) {
            Files.createFile(p);
        }
    }

    public Path getPath(String path) {
        String format = path + ".json";
        return Paths.get(format);
    }

    public boolean exist(String name) {
        Path path = getPath(name);
        return Files.exists(path);
    }

    public void addUnfinishedGame(Timer timer, String date, ArrayList<Player> players, String path) throws IOException {

        ArrayList<Player> play = players;
        FileWriter writer = new FileWriter(path + ".json");
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
            JsonArray jsonArrayBoard = new JsonArray();
            int x = 0, y = 0;
            while (x < 15) {
                while (y < 15) {
                    jsonArrayBoard.add(String.valueOf(board.getTile(x, y)));
                    y++;
                }
                y = 0;
                x++;
            }
            jsonObjectPlayer.add("board", jsonArrayBoard);
            jsonArrayPlayers.add(jsonObjectPlayer);
        }
        jsonObjectGame.add("players", jsonArrayPlayers);

        gson.toJson(jsonObjectGame, writer);
        writer.close();
    }

    public void deleteFile(String gameName) {
        if (exist(gameName)) {
            new File(gameName + ".json").delete();
        }
    }

    public ArrayList<Player> loadGame(String gameName) throws IOException {

        ArrayList<Player> players = new ArrayList<>();
        Path path = getPath(gameName);

        if (Files.exists(path)) {
            JsonObject jsonObjectGame = JsonParser.parseString(Files.readString(path)).getAsJsonObject();
            String time = jsonObjectGame.get("date").getAsString();
            gameManager.updateTimer(time);

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

        JsonArray jsonArrayBoard = player.get("board").getAsJsonArray();

        Board board = new Board();
        Tile[][] tiles = board.getTiles();

        boolean alive = player.get("is_alive").getAsBoolean();

        if (i == 0) {
            //Cargamos al Human
            boolean recharging = player.get("recharging").getAsBoolean();
            int numberAttacks = player.get("number_attacks").getAsInt();
            p = new Human(alive, recharging, numberAttacks, board, gameManager);

        } else {
            //Cargamos al IA
            p = new IA(alive, board, gameManager);
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

        int counter = 0;
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                switch (jsonArrayBoard.get(counter++).getAsString()) {
                    case "WATER":
                        tiles[y][x].setTileType(TileType.WATER);
                        break;
                    case "SHIP":
                        tiles[y][x].setTileType(TileType.SHIP);
                        break;
                    case "HIT":
                        tiles[y][x].setTileType(TileType.HIT);
                        break;
                    case "MISS":
                        tiles[y][x].setTileType(TileType.MISS);
                        break;
                }
            }
        }

        return p;
    }

}