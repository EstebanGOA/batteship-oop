package model;

import model.dao.UserDAO;
import model.entity.User;

public class UserManager {
    private final UserDAO userDao;

    public UserManager(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) { userDao.addUser(user); }

    public void deleteUser(String code) {
        userDao.deleteUser(code);
    }
}
