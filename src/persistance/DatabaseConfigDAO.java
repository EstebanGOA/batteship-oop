package persistance;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseConfigDAO {

    private static final String CONFIG_JSON_PATH = "resources/config.json";

    /**
     *  Funciones para leer el fichero config.json, donde hay los parametros username, password, ip, port y database.
     *
     * @return
     */

    public static String getUsername() {
        try {
            JsonObject jsonConfig = JsonParser.parseString(Files.readString(Paths.get(CONFIG_JSON_PATH))).getAsJsonObject();
            return jsonConfig.get("username").getAsString();
        } catch (IOException e) {
            return "";
        }
    }

    public static String getPassword() {
        try {
            JsonObject jsonConfig = JsonParser.parseString(Files.readString(Paths.get(CONFIG_JSON_PATH))).getAsJsonObject();
            return jsonConfig.get("password").getAsString();
        } catch (IOException e) {
            return "";
        }
    }

    public static String getIp() {
        try {
            JsonObject jsonConfig = JsonParser.parseString(Files.readString(Paths.get(CONFIG_JSON_PATH))).getAsJsonObject();
            return jsonConfig.get("ip").getAsString();
        } catch (IOException e) {
            return "";
        }
    }

    public static int getPort() {
        try {
            JsonObject jsonConfig = JsonParser.parseString(Files.readString(Paths.get(CONFIG_JSON_PATH))).getAsJsonObject();
            return jsonConfig.get("port").getAsInt();
        } catch (IOException e) {
            return 0;
        }
    }

    public static String getDatabaseName() {
        try {
            JsonObject jsonConfig = JsonParser.parseString(Files.readString(Paths.get(CONFIG_JSON_PATH))).getAsJsonObject();
            return jsonConfig.get("database").getAsString();
        } catch (IOException e) {
            return "";
        }
    }
}