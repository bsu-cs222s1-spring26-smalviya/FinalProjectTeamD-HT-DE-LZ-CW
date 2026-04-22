package bsu.edu.cs.gui;

import bsu.edu.cs.user.Login;
import bsu.edu.cs.user.User;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginScreen {
    private VBox view;

    public LoginScreen() {
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton  = new Button("Login");
        Button signupButton = new Button("Sign Up");
        Label  message      = new Label();

        loginButton.setOnAction(e -> {
            Login login = new Login();
            login.setUsername(usernameField.getText());
            login.setPassword(passwordField.getText());

            int id = login.getId();
            if (id != -1) {
                MainApp.setCurrentUser(new User(id));
                MainApp.showMain();
            } else {
                message.setText("Invalid username or password.");
            }
        });

        signupButton.setOnAction(e -> MainApp.showSignup());

        view = new VBox(10, usernameField, passwordField, loginButton, signupButton, message);
        view.setPadding(new Insets(20));
    }

    public VBox getView() {
        return view;
    }
}