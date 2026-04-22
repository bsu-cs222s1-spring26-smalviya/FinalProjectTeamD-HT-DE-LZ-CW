package bsu.edu.cs.gui;

import bsu.edu.cs.user.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static User currentUser;
    public static User getCurrentUser()            { return currentUser; }
    public static void setCurrentUser(User user)   { currentUser = user; }
    public static void logout()                    { currentUser = null; }
    public static boolean isLoggedIn()             { return currentUser != null; }

    private static Stage primaryStage;

    public static void showLogin() {
        LoginScreen screen = new LoginScreen();
        primaryStage.getScene().setRoot(screen.getView());
        primaryStage.setTitle("Login");
    }

    public static void showSignup() {
        SignupScreen screen = new SignupScreen();
        primaryStage.getScene().setRoot(screen.getView());
        primaryStage.setTitle("Sign Up");
    }

    public static void showMain() {
        MainScreen screen = new MainScreen();
        primaryStage.getScene().setRoot(screen.getView());
        primaryStage.setTitle("WorkItOut");
    }

    public static void showFoodLog() {
        FoodInterfaceScreen screen = new FoodInterfaceScreen();
        primaryStage.getScene().setRoot(screen.getView());
        primaryStage.setTitle("Food Log");
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        LoginScreen loginScreen = new LoginScreen();
        Scene scene = new Scene(loginScreen.getView(), 500, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}