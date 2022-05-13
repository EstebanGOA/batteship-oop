package business;

import business.entities.User;
import persistance.UserDAO;
import persistance.sql.SQLUserDAO;

public class UserManager {
    private final UserDAO userDAO;
    private User user;
    private String username;

    public UserManager(SQLUserDAO sqlUserDAO) {
        this.userDAO = sqlUserDAO;
    }

    public boolean addUser(String username, String email, String password) {
        this.user = new User(username, email, password);
        return userDAO.addUser(user);
    }

    public void deleteUser() {
        userDAO.deleteUser(username);
    }

    public boolean checkLogin(String login, String password) {
        if (password.equals(userDAO.getPassword(login))) {
         username = login;

         return true;
        }
        return false;
    }
    public void logoutUser() {
        user = null;
    }

    public String getUsername() {
        return username;
    }
}
