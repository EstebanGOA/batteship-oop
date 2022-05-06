package persistance;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseConfigDAO {
    private static final String CONFIG_JSON_PATH = "resources/config.json";

    public Config readFile() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG_JSON_PATH));
            return new Gson().fromJson (bufferedReader, Config.class);
        } catch (IOException e){
            return null;
        }
    }
}