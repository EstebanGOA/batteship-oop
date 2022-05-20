import business.GameManager;
import business.UserManager;
import business.entities.Timer;
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

        UserManager userManager = new UserManager();
        GameManager gameManager = new GameManager();

        LoginController loginController = new LoginController(loginView, userManager);
        RegisterController registerController = new RegisterController(userManager, registerView);
        SettingsController settingsController = new SettingsController(userManager, settingsView);
        MenuController menuController = new MenuController(userManager, menuView);
        SetupStageController setupStageController = new SetupStageController(setupStageView, gameStageView, gameManager);
        GameController gameController = new GameController(gameStageView, gameManager);

        gameManager.asigneController(gameController);
        mainView.asigneViews(loginView, registerView, menuView, settingsView, setupStageView, gameStageView);

        /* Asignamos los listeners de las vistas a la vista principal */
        loginView.registerMasterView(mainView);
        registerView.registerMasterView(mainView);
        menuView.menuController(menuController);
        registerView.registerController(registerController);
        settingsView.settingsController(settingsController);
        loginView.registerController(loginController);
        setupStageView.registerController(setupStageController);
        gameStageView.registerController(gameController);

        mainView.run();

    }
}
