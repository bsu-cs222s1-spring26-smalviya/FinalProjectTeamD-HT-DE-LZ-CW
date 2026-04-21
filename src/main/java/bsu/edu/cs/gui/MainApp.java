package bsu.edu.cs.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Test Window");

        VBox root = new VBox();
        root.getChildren().add(new Label("Hello World"));

        stage.setScene(new Scene(root, 300, 200));
        stage.show();
    } // end start()

    public static void main(String[] args) {
        launch();
    } // end main
} // close MainApp
