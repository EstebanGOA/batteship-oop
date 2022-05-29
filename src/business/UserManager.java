package business;

import business.entities.User;
import persistance.sql.SQLUserDAO;

import java.util.ArrayList;

/**
 * Class UserManager.
 * This class is used to manage all the aspects of the user.
 */
public class UserManager {

    private SQLUserDAO userDAO;
    private User user;

    /**
     * Constructor of UserManager.
     * @param sqlUserDAO The SQLUserDao to interact with the database.
     */
    public UserManager(SQLUserDAO sqlUserDAO) {
        this.userDAO = sqlUserDAO;
    }

    /**
     * Function that adds a User to the database.
     * @param username A string with the username.
     * @param email A string with the email.
     * @param password A string with the password.
     * @return Returns a boolean regarding if the execution was successful.
     */
    public boolean addUser(String username, String email, String password) {
        this.user = new User(username, email, password);
        return userDAO.addUser(user);
    }

    /**
     * Function that deletes a user from the database.
     * @return Returns a boolean regarding if the operation was successful.
     */
    public boolean delete() {
        if (user != null) {
            userDAO.deleteUser(user.getName());
            return true;
        }
        return false;
    }

    /**
     * Function that checks if the user has logged correctly.
     * @param login A string with the login.
     * @param password A string with the password.
     * @return Returns a boolean regarding if the operation was successful.
     */
    public boolean isLogin(String login, String password) {
        User user = userDAO.getUser(login);

        if (user == null) {
            return false;
        }

        if (user.getPassword().equals(password)) {
            this.user = user;
            return true;
        }
        return false;
    }

    /**
     * Function used to logout a player.
     */
    public void logout() {
        user = null;
    }

    /**
     * Function used to get all the names of the users in the database.
     * @return An array with all the names.
     */
    public ArrayList<String> getUsersName(){
        return userDAO.getUsersName();
    }

    /**
     * Function that gets a User
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Function that get the stats of a player.
     * @param name A string with the name of the player.
     * @return Returns the stats.
     */
    public int[] getWinrate(String name) {
        int[] stats = userDAO.getStats(name);

        return stats;
    }

    /**
     * Function that gets the number of attacks in the games a player has played.
     * @param name The name of the player.
     * @return Returns and array with the attacks.
     */
    public ArrayList<Integer> getAttacks(String name) {
        return userDAO.getNumAttacks(name);
    }
}
