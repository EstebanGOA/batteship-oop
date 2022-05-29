package persistance;


import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class ConfigDAO.
 * This class is in charge of reading the configJson to configure the database and the game.
 */
public class ConfigDAO {

    private String username;
    private String password;
    private String ip;
    private int port;
    private String database;
    private int delay;

    private static final String CONFIG_JSON_PATH = "resources/config.json";

    /**
     * Constructor of ConfigDAO.
     * This constructor reads all the information in the "resources/config.json" file
     */
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

    /**
     * Function that gets the username.
     * @return Returns a string with the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Function that gets the password.
     * @return Returns a string with the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Function that gets the ip.
     * @return Returns a string with the ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Function that gets the port.
     * @return Returns an integer with the port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Function that gets the database name.
     * @return Returns a string with the database name.
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Function that gets the delay.
     * @return Returns an integer with the delay.
     */
    public int getDelay() {
        return delay;
    }
}