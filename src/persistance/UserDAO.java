package persistance;

import business.entities.User;

public interface UserDAO {

    void addUser(User user);
    void deleteUser(String code);
    String getPassword(String string);
}
