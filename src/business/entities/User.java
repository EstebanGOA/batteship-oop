package business.entities;

/**
 * Class User.
 * This class is used to store all the information regarding the user.
 */
public class User {

    private final int id;
    private final String name;
    private final String email;
    private final String password;

    /**
     * Constructor of User, that is used to create a user.
     * @param id an integer regarding the id of the user.
     * @param name an String regarding the name of the user.
     * @param email an String regarding the email of the user.
     * @param password an String regarding the password of the user.
     */
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * This constructor is used to create a temporal user before he is sign up in the database, this user will be passed to
     * the database and the database will be the one to add it and assign an identifier.
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

    /**
     * Function that get the id.
     * @return Returns an integer with the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Function that gets the name of the user.
     * @return Returns a String with the name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Function that gets the email of the user.
     * @return Returns a String with the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Function that gets the password of the user.
     * @return Returns a String with the password of the user.
     */
    public String getPassword() {
        return password;
    }
}
