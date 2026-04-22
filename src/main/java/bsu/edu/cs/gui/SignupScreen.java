package bsu.edu.cs.gui;

import bsu.edu.cs.user.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignupScreen {
    private VBox view;

    public SignupScreen(Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        TextField weightField = new TextField();
        weightField.setPromptText("Weight");

        TextField heightField = new TextField();
        heightField.setPromptText("Height");

        TextField goalField = new TextField();
        goalField.setPromptText("Goal (Loss / Gain / Maintain)");

        TextField activityField = new TextField();
        activityField.setPromptText("Activity Level (1-5)");

        TextField genderField = new TextField();
        genderField.setPromptText("Gender (male / female)");

        Button createButton  = new Button("Create Account");

        Label message = new Label();

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
                    message.setText("Account created successfully!");
                } else {
                    message.setText("Username already exists");
                } // end if
            } catch (Exception ex) {
                message.setText("Invalid input. Please check fields.");
            } // end try/catch
        });

        view = new VBox(10,
                nameField,
                passwordField,
                weightField,
                heightField,
                goalField,
                activityField,
                genderField,
                createButton,
                message
        );
    } // end SignupScreen

    public VBox getView() {
        return view;
    } // end getView
}
