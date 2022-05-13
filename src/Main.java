import business.UserManager;
import persistance.Config;
import persistance.DatabaseConfigDAO;
import persistance.sql.SQLConnector;
import persistance.sql.SQLGameDAO;
import persistance.sql.SQLUserDAO;
import presentation.controllers.LoginController;
import presentation.controllers.MenuController;
import presentation.controllers.RegisterController;
import presentation.controllers.SettingsController;
import presentation.views.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConfigDAO databaseConfigDAO = new DatabaseConfigDAO();
        Config config = databaseConfigDAO.readFile();
        SQLConnector sqlConnector = new SQLConnector(config);
        SQLUserDAO sqlUserDAO = new SQLUserDAO(sqlConnector);
        SQLGameDAO sqlGameDAO = new SQLGameDAO(sqlConnector);
        sqlConnector.connect();

        MainView mainView = new MainView();

        UserManager userManager = new UserManager(sqlUserDAO);


        LoginView loginView = new LoginView(mainView);
        RegisterView registerView = new RegisterView(mainView);
        MenuView menuView = new MenuView(mainView);
        SettingsView settingsView = new SettingsView(mainView);

        LoginController loginController = new LoginController(loginView, userManager);
        RegisterController registerController = new RegisterController(userManager, registerView);
        SettingsController settingsController = new SettingsController(userManager, settingsView);
        MenuController menuController = new MenuController(userManager, menuView);

        mainView.asigneViews(loginView, registerView, menuView, settingsView);

        /* Asignamos los listeners de las vistas a la vista principal */
        loginView.registerMasterView(mainView);
        registerView.registerMasterView(mainView);
        menuView.menuController(menuController);
        registerView.registerController(registerController);
        settingsView.settingsController(settingsController);
        loginView.registerController(loginController);

        mainView.run();
    }
}
