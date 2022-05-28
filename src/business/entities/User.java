package business.entities;

public class User {

    private final int id;
    private final String name;
    private final String email;
    private final String password;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Este constructor creará un usuario temporal antes de ser registrado en la base de datos, se le pasará a la base de datos
     * y que esta se encargue de añadirlo y asignarle un identificador.
     *
     * ATENCIÓN: Solo usar este constructor en caso de ser un usuario temporal, ya que nuestro identificador será nulo y
     * algunas operaciones dejarán de funcionar.
     *
     * @param name Nombre del usuario.
     * @param email Email del usuario.
     * @param password Password del usuario.
     */
    public User(String name, String email, String password) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
