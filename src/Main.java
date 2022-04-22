import business.UserManager;
import presentation.controllers.RegisterController;
import presentation.controllers.SettingsController;
import presentation.views.*;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();

        UserManager userManager = new UserManager();


        LoginView loginView = new LoginView(mainView);
        RegisterView registerView = new RegisterView(mainView);
        MenuView menuView = new MenuView(mainView);
        SettingsView settingsView = new SettingsView(mainView);


        RegisterController registerController = new RegisterController(userManager, registerView);
        SettingsController settingsController = new SettingsController(userManager, settingsView);
        mainView.asigneViews(loginView, registerView, menuView);

        /* Asignamos los listeners de las vistas a la vista principal */
        loginView.registerMasterView(mainView);
        registerView.registerMasterView(mainView);
        menuView.registerMasterView(mainView);
        registerView.registerController(registerController);
        settingsView.settingsController(settingsController);

        mainView.run();
    }
}
