package model.dao;

import model.entity.User;

public interface UserDAO {

    void addUser(User user);
    void deleteUser(String code);
}
