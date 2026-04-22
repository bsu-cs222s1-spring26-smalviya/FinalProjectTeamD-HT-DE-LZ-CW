package bsu.edu.cs.gui;

import bsu.edu.cs.user.NewUser;
import bsu.edu.cs.user.User;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SignupScreen {
    private VBox view;

    public SignupScreen() {
        TextField     nameField     = new TextField();
        nameField.setPromptText("Name");

        TextField     usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        TextField     weightField   = new TextField();
        weightField.setPromptText("Weight (kg)");

        TextField     heightField   = new TextField();
        heightField.setPromptText("Height (cm)");

        TextField     goalField     = new TextField();
        goalField.setPromptText("Goal (Loss / Gain / Maintain)");

        TextField     activityField = new TextField();
        activityField.setPromptText("Activity Level (1-5)");

        TextField     genderField   = new TextField();
        genderField.setPromptText("Gender (male / female)");

        Button createButton = new Button("Create Account");
        Button backButton   = new Button("Back to Login");
        Label  message      = new Label();

        createButton.setOnAction(e -> {
            try {
                int id = (int)(Math.random() * 100000);
                User user = new User(
                        id,
                        usernameField.getText(),
                        passwordField.getText(),
                        nameField.getText(),
                        Double.parseDouble(weightField.getText()),
                        "kg",
                        Double.parseDouble(heightField.getText()),
                        goalField.getText(),
                        Integer.parseInt(activityField.getText()),
                        genderField.getText()
                );

                NewUser newUser = new NewUser();
                if (newUser.createNewUser(user)) {
                    MainApp.setCurrentUser(user);
                    MainApp.showMain();
                } else {
                    message.setText("Username already exists.");
                }
            } catch (Exception ex) {
                message.setText("Invalid input. Please check all fields.");
            }
        });

        backButton.setOnAction(e -> MainApp.showLogin());

        view = new VBox(10,
                nameField,
                usernameField,
                passwordField,
                weightField,
                heightField,
                goalField,
                activityField,
                genderField,
                createButton,
                backButton,
                message
        );
        view.setPadding(new Insets(20));
    }

    public VBox getView() {
        return view;
    }
}