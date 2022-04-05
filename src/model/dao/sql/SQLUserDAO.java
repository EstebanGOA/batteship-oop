package model.dao.sql;

import model.dao.UserDAO;
import model.entity.User;

public class SQLUserDAO implements UserDAO {

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO User(id, name, email, password) VALUES ('" +
                user.getId() + "', '" +
                user.getName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() +
                "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void deleteUser(String code) {
        String query = "DELETE FROM User WHERE name = '" + code + "';";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
