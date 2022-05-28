package persistance.sql;

import persistance.DatabaseConfigDAO;
import java.sql.*;

public class SQLConnector {

    private static SQLConnector instance = null;

    // Attributes to connect to the database.
    private final String username;
    private final String password;
    private final String url;
    private Connection conn;

    public static SQLConnector getInstance() {
        if (instance == null) {
            DatabaseConfigDAO databaseConfigDAO = new DatabaseConfigDAO();
            instance = new SQLConnector(databaseConfigDAO);
            instance.connect();
        }
        return instance;
    }

    // Parametrized constructor
    public SQLConnector(DatabaseConfigDAO databaseConfigDAO) {
        this.username = databaseConfigDAO.getUsername();
        this.password = databaseConfigDAO.getPassword();
        this.url = "jdbc:mysql://" + databaseConfigDAO.getIp() + ":" + databaseConfigDAO.getPort() + "/" + databaseConfigDAO.getDatabase();
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
     * Method that executes an update query to the connected database.
     *
     * @param query String representation of the query to execute.
     */
    public void updateQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problema when updating --> " + e.getSQLState() + " (" + e.getMessage() + ")");
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

    /**
     * Method that closes the inner connection to the database. Ideally, users would disconnect after
     * using the shared instance.
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

}