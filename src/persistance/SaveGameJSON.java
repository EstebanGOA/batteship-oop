package persistance;


import business.entities.*;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveGameJSON {
    private static String path;
    private final Path p;
    private final Gson gson;

    public SaveGameJSON(String nameGame) throws IOException {
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

            jsonObjectPlayer.addProperty("recharging", player.isRecharging());
            System.out.println(i);
            jsonObjectPlayer.addProperty("is_alive", player.isAlive());
            jsonObjectPlayer.addProperty("number_attacks",  player.getNumberOfAttacks());


            Ship[] ships = player.getShips();
            for (int j = 0; j < ships.length; j++) {
                JsonObject jsonObjectShip = new JsonObject();

                jsonObjectShip.addProperty("type", ships[j].getSize());
                jsonObjectShip.addProperty("initial_position", Arrays.toString(ships[j].getPosition()));
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

    private ArrayList<Player> loadGame() throws IOException {
        ArrayList<Player> play = new ArrayList<>();

        if (!Files.readString(p).equals("")) {
            JsonObject jsonObjectGame = JsonParser.parseString(Files.readString(Paths.get(path))).getAsJsonObject();
            String time = jsonObjectGame.get("date").getAsString();

            JsonArray jsonArrayPlayers = jsonObjectGame.getAsJsonArray("players").getAsJsonArray();
            for (int i = 0; i < jsonArrayPlayers.size(); i++) {
                play.add(playerFromJson(jsonArrayPlayers.get(i).getAsJsonObject(), i));
            }
        }
        return play;
    }

    private Player playerFromJson(JsonObject player, int i) {
        boolean recharging = player.get("recharging").getAsBoolean();
        boolean isAlive = player.get("is_alive").getAsBoolean();
        int numberAttacks = player.get("number_attacks").getAsInt();

        if (i == 0) {
            //return new Human()
        } else {
            //return new IA()
        }
    }
}