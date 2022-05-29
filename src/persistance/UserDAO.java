package persistance;

import business.entities.User;

import java.util.ArrayList;

public interface UserDAO {


    boolean addUser(User user);

    boolean deleteUser(String code);

    User getUser(String string);

    ArrayList<String> getUsersName();

    int[] getStats(String string);

    ArrayList<Integer> getNumAttacks(String string);

}
