package persistance.sql;

import persistance.UserDAO;
import business.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {
    private final SQLConnector sqlConnector;

    public SQLUserDAO(SQLConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
    }

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO User(name, email, password) VALUES ('" +
                user.getName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() +
                "');";

        return sqlConnector.insertQuery(query);
    }

    @Override
    public boolean deleteUser(String code) {
        String query = "DELETE FROM User WHERE name = '" + code + "';";

        return sqlConnector.deleteQuery(query);
    }

    public String getPassword(String string) {
        String query;

        if (string.contains("@")) {
            query = "SELECT * FROM User WHERE email = '" + string + "';";
        } else {
            query = "SELECT * FROM User WHERE name = '" + string + "';";
        }

        ResultSet result = sqlConnector.selectQuery(query);

        try {
            //Comprobamos si el usuario existe.
            if (result.next()) {
                //Accedemos a la cuarta columna de la tabla User, es decir, retornamos la constraseña asociada al usuario.
                return result.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}