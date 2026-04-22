package bsu.edu.cs.gui;

import bsu.edu.cs.user.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        LoginScreen loginScreen =  new LoginScreen(stage);

        Scene scene = new Scene(loginScreen.getView(), 400, 300);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    } // end start()

    public static void main(String[] args) {
        launch();
    } // end main
} // close MainApp
