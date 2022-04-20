import business.UserManager;
import presentation.controllers.LoginController;
import presentation.controllers.RegisterController;
import presentation.views.LoginView;
import presentation.views.MainView;
import presentation.views.MenuView;
import presentation.views.RegisterView;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();

        UserManager userManager = new UserManager();


        LoginView loginView = new LoginView(mainView);
        RegisterView registerView = new RegisterView(mainView);
        MenuView menuView = new MenuView(mainView);

        LoginController loginController = new LoginController(loginView, userManager);
        RegisterController registerController = new RegisterController(userManager, registerView);

        mainView.asigneViews(loginView, registerView, menuView);

        /* Asignamos los listeners de las vistas a la vista principal */
        loginView.registerMasterView(loginController);
        registerView.registerMasterView(mainView);
        menuView.registerMasterView(mainView);
        registerView.registerController(registerController);

        mainView.run();
    }
}
