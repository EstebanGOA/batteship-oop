import model.UserManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        UserManager u = new UserManager();
        System.out.println(u.getPassword("Kevin"));
        new LoginView();
    }
}
