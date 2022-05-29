package persistance;

import business.entities.User;

import java.util.ArrayList;

/**
 * Interface UserDAO
 */
public interface UserDAO {

    /**
     * Function used to add a user in the database.
     * @param user The user we want to add.
     * @return A boolean whether the execution was successful or not.
     */
    boolean addUser(User user);

    /**
     * Function used to delete a user from the database.
     * @param code A string with the name of user we want to delete.
     * @return A boolean whether the execution was successful or not.
     */
    boolean deleteUser(String code);

    /**
     * Function used to get a User from the database with his name or email.
     * @param string A string with the name or the email of the user.
     * @return The user with that name or email.
     */
    User getUser(String string);

    /**
     * Function used to retrieve all usernames from database.
     * @return It will return all usernames from database stored inside the array.
     */
    ArrayList<String> getUsersName();

    /**
     * Function used to retrieve statistics of a player from database.
     * @param string Username.
     * @return Array that it will contain statistics from the user.
     */
    int[] getStats(String string);

    /**
     * Function used to retrieve statistics of a player from database.
     * @param string Username.
     * @return Array that it will contain statistics from the user.
     */
    ArrayList<Integer> getNumAttacks(String string);
}
