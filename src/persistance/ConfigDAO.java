package persistance;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigDAO {

    private String username;
    private String password;
    private String ip;
    private int port;
    private String database;
    private int delay;

    private static final String CONFIG_JSON_PATH = "resources/config.json";

    public ConfigDAO() {
        JsonObject jsonConfig = null;
        try {
            jsonConfig = JsonParser.parseString(Files.readString(Paths.get(CONFIG_JSON_PATH))).getAsJsonObject();
            username = jsonConfig.get("username").getAsString();
            password = jsonConfig.get("password").getAsString();
            ip = jsonConfig.get("ip").getAsString();
            port = jsonConfig.get("port").getAsInt();
            database = jsonConfig.get("database").getAsString();
            delay = jsonConfig.get("delay").getAsInt();
        } catch (IOException e) {
            System.out.println("Couldn't read data from config.json");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public int getDelay() {
        return delay;
    }

}