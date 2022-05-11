package persistance;

import business.entities.User;

import java.util.ArrayList;

public interface UserDAO {


    boolean addUser(User user);

    boolean deleteUser(String code);

    String getPassword(String string);

    ArrayList<String> getUsersName();
}
