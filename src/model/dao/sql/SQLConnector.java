package model.dao.sql;

import java.sql.*;

public class SQLConnector {

    private static model.dao.sql.SQLConnector instance = null;

    public static model.dao.sql.SQLConnector getInstance() {
        if (instance == null) {
            // NOT a good practice to hardcode connection data! Be aware of this for your project delivery ;)
            instance = new model.dao.sql.SQLConnector("Kevin", "1234", "localhost", 3306, "battleship");
            instance.connect();
        }
        return instance;
    }

    // Attributes to connect to the database.
    private final String username;
    private final String password;
    private final String url;
    private Connection conn;

    // Parametrized constructor
    public SQLConnector(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
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
    public void insertQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
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
    public void deleteQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
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