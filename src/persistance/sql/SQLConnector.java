package persistance.sql;

import persistance.ConfigDAO;

import java.sql.*;

/**
 * Class SQLConnector used to connect the java with the SQL database
 */
public class SQLConnector {

    private static persistance.sql.SQLConnector instance = null;

    // Attributes to connect to the database.
    private final String username;
    private final String password;
    private final String url;
    private Connection conn;

    /**
     * Function that gets the instance of the SQLConnector and returns it, if the instance is null then we created.
     * @return Returns the instance of the SQLConnector.
     */
    public static SQLConnector getInstance() {
        if (instance == null) {
            ConfigDAO configDAO = new ConfigDAO();
            instance = new SQLConnector(configDAO);
            instance.connect();
        }
        return instance;
    }

    /**
     * Constructor of SQLConnector.
     * @param configDAO The configDAO where we get all the information related with the database.
     */
    public SQLConnector(ConfigDAO configDAO) {
        this.username = configDAO.getUsername();
        this.password = configDAO.getPassword();
        this.url = "jdbc:mysql://" + configDAO.getIp() + ":" + configDAO.getPort() + "/" + configDAO.getDatabase();
    }


    /**
     * Method that starts the inner connection to the database. Ideally, users would disconnect after
     * using the shared instance.
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
    }

    /**
     * Method that executes an insertion query to the connected database.
     *
     * @param query String representation of the query to execute.
     */
    public boolean insertQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            return false;
        }
    }



    /**
     * Method that executes a deletion query to the connected database.
     *
     * @param query String representation of the query to execute.
     */
    public boolean deleteQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            return false;
        }
    }

    /**
     * Method that executes a selection query to the connected database.
     *
     * @param query String representation of the query to execute.
     * @return The results of the selection.
     */
    public ResultSet selectQuery(String query) {
        ResultSet rs = null;
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return rs;
    }
}