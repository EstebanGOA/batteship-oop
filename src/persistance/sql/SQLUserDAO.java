package persistance.sql;

import persistance.UserDAO;
import business.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class SQLUserDAO that implements UserDAO.
 * This class is responsible to communicate with the database, on all the matters regarding the user.
 */
public class SQLUserDAO implements UserDAO {

    private final SQLConnector sqlConnector;

    public SQLUserDAO() {
        this.sqlConnector = SQLConnector.getInstance();
    }

    /**
     * Function used to add a user in the database.
     * @param user The user we want to add.
     * @return A boolean whether the execution was successful or not.
     */
    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO User(name, email, password) VALUES ('" +
                user.getName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() +
                "');";

        return sqlConnector.insertQuery(query);
    }

    /**
     * Function used to delete a user from the database.
     * @param code A string with the name of user we want to delete.
     * @return A boolean whether the execution was successful or not.
     */
    @Override
    public boolean deleteUser(String code) {
        String query = "DELETE FROM User WHERE name = '" + code + "';";

        return sqlConnector.deleteQuery(query);
    }

    /**
     * Function used to get a User from the database with his name or email.
     * @param string A string with the name or the email of the user.
     * @return The user with that name or email.
     */
    @Override
    public User getUser(String string) {
        String query;

        if (string.contains("@")) {
            query = "SELECT * FROM User WHERE email = '" + string + "';";
        } else {
            query = "SELECT * FROM User WHERE name = '" + string + "';";
        }

        ResultSet result = sqlConnector.selectQuery(query);

        try {
            //Comprobamos si el usuario existe.
            if (result.next()) {
                //Accedemos a la cuarta columna de la tabla User, es decir, retornamos la constraseña asociada al usuario.
                int id = result.getInt(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                return new User(id, username, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Function that will get all usernames from the database.
     * @return Array with all the username saved on the databse.
     */
    public ArrayList<String> getUsersName() {
        String query = "SELECT name FROM User";

        ArrayList<String> users = new ArrayList<>();


        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            //Comprobamos si el usuario existe.



                while(result.next()) {
                    users.add(result.getString("name"));

                }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    /**
     * Function that will get the statistics from one user.
     * @param user Username.
     * @return Array with the statistics data inside.
     */
    @Override
    public int[] getStats(String user) {
        String query;
        int games_played = 0;
        int games_won = 0;

        query = "SELECT COUNT(g.id) FROM game AS g " +
                "JOIN user AS u " +
                "WHERE u.id = g.player_id AND u.name LIKE '%" +user +"%';";

        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            //Comprobamos si el usuario existe.
            if (result.next()) {
                //Accedemos a la cuarta columna de la tabla User, es decir, retornamos la constraseña asociada al usuario.
               games_played  = result.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT COUNT(g.id) FROM game AS g " +
                "JOIN user AS u " +
                "WHERE u.id = g.player_id AND g.win = 1 AND u.name LIKE '%" +user +"%';";

        result = SQLConnector.getInstance().selectQuery(query);

        try {
            //Comprobamos si el usuario existe.
            if (result.next()) {
                //Accedemos a la cuarta columna de la tabla User, es decir, retornamos la constraseña asociada al usuario.
               games_won  = result.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new int[] {games_won, games_played};


    }

    /**
     * Function that will get the number of attacks made from one user.
     * @param string Username.
     * @return Array with the statistics data inside.
     */
    @Override
    public ArrayList<Integer> getNumAttacks(String string) {

        String query = "SELECT g.number_of_attacks " +
                "FROM user AS u " +
                "JOIN game AS g ON u.id = g.player_id " +
                "WHERE u.name LIKE '%" + string +"%' " +
                "ORDER BY g.id DESC;";

        ArrayList<Integer> num_attacks = new ArrayList<>();
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            //Comprobamos si el usuario existe.
                while(result.next()) {
                    num_attacks.add(result.getInt("number_of_attacks"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num_attacks;
    }
}