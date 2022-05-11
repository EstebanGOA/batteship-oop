package persistance.sql;

import persistance.UserDAO;
import business.entities.User;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public String getPassword(String string) {
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
                return result.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<String> getUsersName() {
        String query = "SELECT name FROM User";

        ArrayList<String> users = new ArrayList<>();


        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            //Comprobamos si el usuario existe.
            if (result.next()) {


                while(result.next()) {
                    users.add(result.getString("name"));

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }
}
