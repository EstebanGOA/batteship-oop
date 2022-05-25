package persistance;


import business.entities.Board;
import business.entities.Player;
import business.entities.Ship;
import business.entities.TileType;
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

public class SaveGameJSON {
    private static final String path = "data/" + "nombrePartida" + ".json";
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

    public void addUnfinishedGame(ArrayList<Player> players) throws IOException {
        ArrayList<Player> play = players;
        FileWriter writer = new FileWriter(path);
        JsonObject jsonObjectGame = new JsonObject();

        //Al principio del fichero nos guardamos: el nombre de la partida, la fecha de registro y su tiempo transcurrido.
        jsonObjectGame.addProperty("game_name", "Kevin");
        jsonObjectGame.addProperty("date", 45);
        jsonObjectGame.addProperty("time", "Kevin");

        JsonArray jsonArrayPlayers = new JsonArray();
        JsonObject jsonObjectPlayer = new JsonObject();

        //Por cada jugador nos guardamos: el nombre, sus barcos y el estado de su tablero.
        for (int i = 0; i < play.size(); i++) {
            Ship[] ships = play.get(i).getShips();
            for (int j = 0; j < ships.length; j++) {
                //Por cada barco del jugador, nos guardaremos el tipo, la posicion inicial y la orientación.
                int[] initialPosition = ships[j].getPosition();
                String orientation = ships[j].getOrientation();
                //Falta pasarle el tipo.
            }

            Board board = play.get(i).getBoard();
            int x = 0, y = 0;
            while (x <= 15) {
                while (y <= 15) {
                    TileType statusCell = board.getTile(x, y);
                    y++;
                }
                x++;
            }
        }
    }


    //gson.toJson(trials, writer);
    //writer.close();
}