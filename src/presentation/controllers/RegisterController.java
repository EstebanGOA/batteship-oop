package presentation.controllers;

import business.UserManager;
import business.entities.User;
import presentation.views.RegisterView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Controlador que se encargará de la gestión para el registro de usuario.
 */
public class RegisterController implements MouseListener {

    private final UserManager userManager;
    private RegisterView registerView;

    public RegisterController(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Recogerá la información de la vista, comprobará la información introducida e informará al usuario si ha ocurrido algún error,
     * en caso de que no ocurra cambiará la vista, ya que se habrá completado el registro.
     */
    public void processRequest() {
        HashMap<String, String> data = registerView.getData();

        if (data.get("username").isEmpty() || data.get("email").isEmpty() || data.get("password").isEmpty() || data.get("password_verify").isEmpty()) {
            registerView.cleanPassword();
            registerView.popupMessage("Error, todos los campos son obligatorios.");
            return;
        }

        if (!data.get("password").equals(data.get("password_verify"))) {
            registerView.cleanPassword();
            registerView.popupMessage("Error, las contraseñas no coinciden.");
            return;
        }

        String username = data.get("username");
        String email = data.get("email");
        String password = data.get("password");

        registerView.cleanFormulary();

        if (userManager.addUser(username, email, password)) {
            registerView.popupMessage("Account created successfully!");
            registerView.registerCompleted();
        } else {
            registerView.popupMessage("Error, ya existe un usuario con los datos introducidos.");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JComponent) {
            switch (((JComponent) e.getSource()).getName()) {
                case "register" -> processRequest();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void asigneView(RegisterView registerView) {
        this.registerView = registerView;
    }
}
