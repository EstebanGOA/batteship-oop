package business;

import business.entities.User;
import persistance.UserDAO;
import persistance.sql.SQLUserDAO;

public class UserManager {

    private final UserDAO userDao;

    private String username;

    public UserManager() {
        userDao = new SQLUserDAO();
    }

    public boolean addUser(String username, String email, String password) {
        User user = new User(username, email, password);
        return userDao.addUser(user);
    }

    public void deleteUser(String code) {
        userDao.deleteUser(code);
    }

    public boolean checkLogin(String login, String password) {
        if (password.equals(userDao.getPassword(login))) {
         username = login;
         return true;
        }
        return false;
    }



}
