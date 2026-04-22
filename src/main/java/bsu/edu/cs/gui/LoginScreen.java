package bsu.edu.cs.gui;

import bsu.edu.cs.user.*;
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
        Button signupButton = new Button("Sign Up");

        Label message = new Label();

        loginButton.setOnAction(e -> {
            Login login = new Login();
            login.setUsername(usernameField.getText());
            login.setPassword(passwordField.getText());

            int id = login.getId();

            if( id != -1) {
                message.setText("Login successful!");
            } else {
                message.setText("Invalid username or password");
            } // end if
        });

        signupButton.setOnAction(e -> {
            SignupScreen signup = new SignupScreen(stage);
            stage.getScene().setRoot(signup.getView());
        });

        view = new VBox(10, usernameField, passwordField, loginButton, signupButton, message);
    } // end LoginScreen

    public VBox getView() {
        return view;
    } // end getView
} // close LoginScreen
