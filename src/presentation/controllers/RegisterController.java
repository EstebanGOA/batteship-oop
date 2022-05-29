package presentation.controllers;

import business.UserManager;
import presentation.views.RegisterView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * RegisterController class that implements a MouseListener.
 * The RegisterController handles user registration
 */
public class RegisterController implements MouseListener {

    private final UserManager userManager;
    private RegisterView registerView;

    public RegisterController(UserManager userManager, RegisterView registerView) {
        this.userManager = userManager;
        this.registerView = registerView;
    }

    /**
     * Function that takes care of validating the email.
     * @param email A string with the email.
     */
    public boolean isValidEmailAddress(String email) {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return (email.trim().matches(emailPattern));
    }

    /**
     * Function that takes care of validating the password.
     * @param password A string with the password.
     */
    public boolean isValidPassword(String password) {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8) {
            registerView.popupMessage("Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            registerView.popupMessage("Password must have at least one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)) {
            registerView.popupMessage("Password must have at least one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            registerView.popupMessage("Password must have at least one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars)) {
            registerView.popupMessage("Password must have at least one special character among @#$%");
            isValid = false;
        }
        return isValid;
    }

    /**
     * Function that takes care of validating the username.
     * @param name A string with the username.
     */
    public boolean isValidName(String name) {
        String namePattern = "[a-zA-Z]+";
        return (name.trim().matches(namePattern));
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

        } else {

            if (!isValidName(data.get("username"))) {
                registerView.cleanName();
                registerView.popupMessage("Error, el formato del nombre es incorrecto.");
                return;
            }

            if (!isValidEmailAddress(data.get("email"))) {
                registerView.cleanEmail();
                registerView.popupMessage("Error, el formato del email es incorrecto.");
                return;
            }

            if (!isValidPassword(data.get("password"))) {
                registerView.cleanPassword();
                return;
            }

            if (data.get("password").equals(data.get("password_verify"))) {

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

            } else {

                registerView.cleanPassword();
                registerView.popupMessage("Error, las contraseñas no coinciden.");

            }

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
}
