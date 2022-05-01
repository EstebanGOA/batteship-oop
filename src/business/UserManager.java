package business;

import business.entities.User;
import persistance.UserDAO;
import persistance.sql.SQLUserDAO;

public class UserManager {

    private final UserDAO userDao;
    private User user;

    public UserManager() {
        userDao = new SQLUserDAO();
    }

    public boolean addUser(String username, String email, String password) {
        this.user = new User(username, email, password);
        return userDao.addUser(user);
    }

    public void deleteUser(String code) {
        userDao.deleteUser(code);
    }

    public String getPassword(String string) {
        return userDao.getPassword(string);
    }
    public void logoutUser() {
        user = null;
    }

}
