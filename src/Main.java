import business.UserManager;
import presentation.controllers.RegisterController;
import presentation.views.LoginView;
import presentation.views.MainView;
import presentation.views.MenuView;
import presentation.views.RegisterView;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();

        UserManager userManager = new UserManager();

        RegisterController registerController = new RegisterController(userManager);

        LoginView loginView = new LoginView(mainView);
        RegisterView registerView = new RegisterView(mainView);
        MenuView menuView = new MenuView(mainView);

        mainView.asigneViews(loginView, registerView, menuView);

        /* Asignamos las vistas al MainView */
        loginView.registerMasterView(mainView);
        registerView.registerMasterView(mainView);
        menuView.registerMasterView(mainView);
        registerView.registerController(registerController);

        /* Asignamos las vistas a los controladores */
        registerController.asigneView(registerView);


        mainView.run();
    }
}
