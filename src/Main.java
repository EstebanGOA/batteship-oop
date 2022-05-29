import business.GameManager;
import business.UserManager;
import persistance.sql.SQLGameDAO;
import persistance.sql.SQLUserDAO;
import presentation.controllers.*;
import presentation.views.*;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();

        LoginView loginView = new LoginView(mainView);
        RegisterView registerView = new RegisterView(mainView);
        MenuView menuView = new MenuView(mainView);
        SettingsView settingsView = new SettingsView(mainView);
        SetupStageView setupStageView = new SetupStageView(mainView);
        GameStageView gameStageView = new GameStageView(mainView);

        SQLUserDAO sqlUserDAO = new SQLUserDAO();
        UserManager userManager = new UserManager(sqlUserDAO);
        SQLGameDAO sqlGameDAO = new SQLGameDAO(userManager);
        GameManager gameManager = new GameManager(sqlGameDAO);
        StatisticsView statisticsView = new StatisticsView(mainView);

        LoginController loginController = new LoginController(loginView, userManager);
        RegisterController registerController = new RegisterController(userManager, registerView);
        SettingsController settingsController = new SettingsController(userManager, settingsView);
        MenuController menuController = new MenuController(userManager, gameManager, menuView, gameStageView);
        StatisticsController statisticsController = new StatisticsController(userManager, statisticsView);
        SetupStageController setupStageController = new SetupStageController(setupStageView, gameStageView, gameManager);
        GameController gameController = new GameController(gameStageView, gameManager);

        gameManager.assignController(gameController);
        mainView.assignViews(loginView, registerView, menuView, settingsView, statisticsView,  setupStageView, gameStageView);

        /* Asignamos los listeners de las vistas a la vista principal */
        loginView.registerMasterView(mainView);
        registerView.registerMasterView(mainView);
        menuView.menuController(menuController);
        registerView.registerController(registerController);
        settingsView.settingsController(settingsController);
        loginView.registerController(loginController);
        setupStageView.registerController(setupStageController);
        gameStageView.registerController(gameController);
        statisticsView.menuController(statisticsController);

        mainView.run();
    }
}