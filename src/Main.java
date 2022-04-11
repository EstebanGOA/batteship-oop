import business.UserManager;
import presentation.views.LoginView;
import presentation.views.RegisterView;
import presentation.views.SettingsView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        UserManager u = new UserManager();
        System.out.println(u.getPassword("Kevin"));
        new LoginView();
    }
}
