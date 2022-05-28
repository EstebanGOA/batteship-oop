package business;

import business.entities.User;
import persistance.UserDAO;
import persistance.sql.SQLUserDAO;

import java.util.ArrayList;

public class UserManager {

    private SQLUserDAO userDAO;
    private User user;

    public UserManager(SQLUserDAO sqlUserDAO) {
        this.userDAO = sqlUserDAO;
    }

    public boolean addUser(String username, String email, String password) {
        this.user = new User(username, email, password);
        return userDAO.addUser(user);
    }

    public boolean delete() {
        if (user != null) {
            userDAO.deleteUser(user.getName());
            return true;
        }
        return false;
    }

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

    public void logout() {
        user = null;
    }

    public ArrayList<String> getUsersName(){
        return userDAO.getUsersName();
    }


    public User getUser() {
        return user;
    }

    public int[] getWinrate(String name) {
        int[] stats = userDAO.getStats(name);

        return stats;
    }
    public ArrayList<Integer> getAttacks(String name) {
        return userDAO.getNumAttacks(name);
    }

}
