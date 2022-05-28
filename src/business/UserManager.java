package business;

import business.entities.User;
import persistance.UserDAO;
import persistance.sql.SQLUserDAO;

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

    public User getUser() {
        return user;
    }

}
