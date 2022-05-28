package persistance;

import business.entities.User;

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
}
