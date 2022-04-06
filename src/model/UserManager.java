package model;

import model.dao.UserDAO;
import model.dao.sql.SQLUserDAO;
import model.entity.User;

public class UserManager {
    private final UserDAO userDao;

    public UserManager() {
        userDao = new SQLUserDAO();
    }

    public void addUser(User user) { userDao.addUser(user); }

    public void deleteUser(String code) { userDao.deleteUser(code); }

    public String getPassword(String string) { return userDao.getPassword(string); }
}
