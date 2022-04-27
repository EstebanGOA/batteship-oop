import business.UserManager;
import presentation.views.LoginView;
import presentation.views.MainView;
import presentation.views.MenuView;
import presentation.views.RegisterView;
import presentation.views.SettingsView;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.run();
    }
}
