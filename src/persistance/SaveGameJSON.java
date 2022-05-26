package persistance;


import business.entities.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class SaveGameJSON {
    private static final String path = "kevin" + ".json";
    private final Path p;
    private final Gson gson;
    //private ArrayList<Player> jugadores;

    public SaveGameJSON() throws IOException {
        p = Paths.get(path);
        gson = new GsonBuilder().setPrettyPrinting().create();

        if (!Files.exists(p)) {
            Files.createFile(p);
            //this.jugadores = new ArrayList<>();
        }
    }

    public void addUnfinishedGame(String timer, String date, ArrayList<Player> players) throws IOException {
        ArrayList<Player> play = players;
        FileWriter writer = new FileWriter(path);
        JsonObject jsonObjectGame = new JsonObject();

        jsonObjectGame.addProperty("game_name", "Kevin");
        jsonObjectGame.addProperty("date", date);
        System.out.println(timer);
        //jsonObjectGame.addProperty("time", timer);

        JsonArray jsonArrayPlayers = new JsonArray();

        for (int i = 0; i < play.size(); i++) {
            JsonArray jsonArrayShips = new JsonArray();
            JsonObject jsonObjectPlayer = new JsonObject();

            jsonObjectPlayer.addProperty("recharging", play.get(i).isRecharging());
            jsonObjectPlayer.addProperty("is_alive", play.get(i).isAlive());
            jsonObjectPlayer.addProperty("number_attacks",  play.get(i).getNumberOfAttacks());


            Ship[] ships = play.get(i).getShips();
            for (int j = 0; j < ships.length; j++) {
                JsonObject jsonObjectShip = new JsonObject();

                jsonObjectShip.addProperty("type", ships[j].getSize());
                jsonObjectShip.addProperty("initial_position", Arrays.toString(ships[j].getPosition()));
                jsonObjectShip.addProperty("orientation", ships[j].getOrientation());

                jsonArrayShips.add(jsonObjectShip);
            }
            jsonObjectPlayer.add("ships", jsonArrayShips);

            Board board = play.get(i).getBoard();
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
}