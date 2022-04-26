package persistance;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseInformationDAO {

    private static final String CONFIG_JSON_PATH = "./resources/config.json";

    /**
     *  Funcion para leer el fichero config.json, donde hay los parametros username, password, ip, port y database.
     *
     * @return
     */
    public InfoFromFile readFile(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG_JSON_PATH));
            return new Gson().fromJson (bufferedReader, InfoFromFile.class);
        } catch (IOException e){
            return null;
        }
    }
}
