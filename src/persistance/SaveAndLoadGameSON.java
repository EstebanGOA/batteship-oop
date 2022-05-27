package persistance;


import business.GameManager;
import business.entities.*;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SaveAndLoadGameSON {
    private static String path;
    private final Path p;
    private final Gson gson;
    GameManager gameManager = new GameManager();

    public SaveAndLoadGameSON(String nameGame) throws IOException {
        path = nameGame + ".json";
        p = Paths.get(path);
        gson = new GsonBuilder().setPrettyPrinting().create();

        //Si no existe lo crea sino lo sobrescribe.
        if (!Files.exists(p)) {
            Files.createFile(p);
        }
    }

    public void addUnfinishedGame(Timer timer, String date, ArrayList<Player> players) throws IOException {
        ArrayList<Player> play = players;
        FileWriter writer = new FileWriter(path);
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
        File file = new File(gameName + ".json");
        if (file.exists()) {
            file.delete();
        }
    }

    public ArrayList<Player> loadGame() throws IOException {
        ArrayList<Player> play = new ArrayList<>();

        if (Files.exists(p)) {
            JsonObject jsonObjectGame = JsonParser.parseString(Files.readString(Paths.get(path))).getAsJsonObject();
            String time = jsonObjectGame.get("date").getAsString();
            gameManager.updateTimer(time);

            JsonArray jsonArrayPlayers = jsonObjectGame.getAsJsonArray("players").getAsJsonArray();
            for (int i = 0; i < jsonArrayPlayers.size(); i++) {
                play.add(playerFromJson(jsonArrayPlayers.get(i).getAsJsonObject(), i));
            }
        }
        return play;
    }


    private Player playerFromJson(JsonObject player, int i) {
        boolean isAlive = player.get("is_alive").getAsBoolean();

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
                    gameManager.insertShip(initialPosition, "Boat" ,orientation);
                    break;
                case 3:
                    if (flag) {
                        gameManager.insertShip(initialPosition, "Submarine2" ,orientation);
                    } else {
                        gameManager.insertShip(initialPosition, "Submarine1" ,orientation);
                        flag = true;
                    }
                    break;
                case 4:
                    gameManager.insertShip(initialPosition, "Destructor" ,orientation);
                    break;
                case 5:
                    gameManager.insertShip(initialPosition,"Aircraft" ,orientation);
                    break;
            }
         }

        JsonArray jsonArrayBoard = new JsonArray();
        Board board = new Board();
        Tile[][] tiles = board.getTiles();

        int x = 0;
        while (x < 15) {
            int y = 0;
            while (y < 15) {
                switch (jsonArrayBoard.get(y).getAsString()) {
                    case "WATER":
                        tiles[x][y].setTileType(TileType.WATER);
                        break;
                    case "SHIP":
                        tiles[x][y].setTileType(TileType.SHIP);
                        break;
                    case "HIT":
                        tiles[x][y].setTileType(TileType.HIT);
                        break;
                    case "MISS":
                        tiles[x][y].setTileType(TileType.MISS);
                        break;
                }
                y++;
            }
        }

        if (i == 0) {
            //Cargamos al Human
            boolean recharging = player.get("recharging").getAsBoolean();
            AtomicInteger numberAttacks = new AtomicInteger(player.get("number_attacks").getAsInt());

            Human human = new Human(board, gameManager);

            human.setAlive(isAlive);
            human.setRecharging(recharging);
            human.setNumberOfAttacks(numberAttacks);

            return human;
        } else {
            //Cargamos al IA
            IA ia = new IA(board, gameManager);

            ia.setAlive(isAlive);
            return ia;
        }
    }
}