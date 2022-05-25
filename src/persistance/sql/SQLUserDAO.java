package persistance.sql;

import persistance.UserDAO;
import business.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO User(name, email, password) VALUES ('" +
                user.getName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() +
                "');";

        return SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public boolean deleteUser(String code) {
        String query = "DELETE FROM User WHERE name = '" + code + "';";
        return SQLConnector.getInstance().deleteQuery(query);
    }
    public User getUser(String string) {
        String query;

        if (string.contains("@")) {
            query = "SELECT * FROM User WHERE email = '" + string + "';";
        } else {
            query = "SELECT * FROM User WHERE name = '" + string + "';";
        }

        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            //Comprobamos si el usuario existe.
            if (result.next()) {
                //Accedemos a la cuarta columna de la tabla User, es decir, retornamos la constraseña asociada al usuario.
                int id = result.getInt(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                return new User(id, username, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
