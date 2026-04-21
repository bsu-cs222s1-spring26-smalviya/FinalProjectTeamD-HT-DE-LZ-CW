package bsu.edu.cs.gui;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {
    private VBox view;

    public LoginScreen(Stage stage) {
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        Label message = new Label();

        view = new VBox(10, usernameField, passwordField, loginButton, message);
    } // end LoginScreen

    public VBox getView() {
        return view;
    } // end getView
} // close LoginScreen
