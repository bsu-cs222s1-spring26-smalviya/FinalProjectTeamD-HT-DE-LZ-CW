package bsu.edu.cs.gui;

import bsu.edu.cs.user.User;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MainScreen {
    private VBox view;

    public MainScreen() {
        User user = MainApp.getCurrentUser();

        Label welcome = new Label("Welcome, " + user.getName() + "!");
        welcome.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button foodLogBtn  = new Button("Food Logger");
        Button settingsBtn = new Button("Settings");
        Button logoutBtn   = new Button("Logout");

        foodLogBtn .setMaxWidth(Double.MAX_VALUE);
        settingsBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn  .setMaxWidth(Double.MAX_VALUE);

        foodLogBtn .setOnAction(e -> MainApp.showFoodLog());
        settingsBtn.setOnAction(e -> MainApp.showSettings());
        logoutBtn  .setOnAction(e -> { MainApp.logout(); MainApp.showLogin(); });

        view = new VBox(15, welcome, foodLogBtn, settingsBtn, logoutBtn);
        view.setPadding(new Insets(30));
    }

    public VBox getView() {
        return view;
    }
}