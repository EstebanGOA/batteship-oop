import business.UserManager;
import presentation.controllers.*;
import presentation.views.*;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();

        UserManager userManager = new UserManager();


        LoginView loginView = new LoginView(mainView);
        RegisterView registerView = new RegisterView(mainView);
        MenuView menuView = new MenuView(mainView);
        SettingsView settingsView = new SettingsView(mainView);
        StatisticsView statisticsView = new StatisticsView(mainView);

        LoginController loginController = new LoginController(loginView, userManager);
        RegisterController registerController = new RegisterController(userManager, registerView);
        SettingsController settingsController = new SettingsController(userManager, settingsView);
        MenuController menuController = new MenuController(userManager, menuView);
        StatisticsController statisticsController = new StatisticsController(userManager, statisticsView);

        mainView.asigneViews(loginView, registerView, menuView, settingsView, statisticsView);

        /* Asignamos los listeners de las vistas a la vista principal */
        loginView.registerMasterView(mainView);
        registerView.registerMasterView(mainView);
        menuView.menuController(menuController);
        registerView.registerController(registerController);
        settingsView.settingsController(settingsController);
        loginView.registerController(loginController);
        statisticsView.menuController(statisticsController);

        mainView.run();
    }
}
