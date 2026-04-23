package bsu.edu.cs.gui;

import bsu.edu.cs.user.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SettingsScreen {
    private VBox view;

    public SettingsScreen() {
        User user = MainApp.getCurrentUser();

        Label title = new Label("Settings");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Content area that swaps based on which option the user picks
        VBox contentArea = new VBox(10);
        contentArea.setPadding(new Insets(10, 0, 0, 0));

        // --- Menu buttons, same order as openSettingsMenu ---
        Button nameBtn     = new Button("(1) Name");
        Button usernameBtn = new Button("(2) Username");
        Button passwordBtn = new Button("(3) Password");
        Button weightBtn   = new Button("(4) Weight");
        Button heightBtn   = new Button("(5) Height");
        Button goalBtn     = new Button("(6) Goal");
        Button activityBtn = new Button("(7) Activity Level");
        Button backBtn     = new Button("(8) Return to Main Menu");

        for (Button b : new Button[]{nameBtn, usernameBtn, passwordBtn, weightBtn,
                heightBtn, goalBtn, activityBtn, backBtn}) {
            b.setMaxWidth(Double.MAX_VALUE);
        }

        // --- Wire each button to its matching panel ---
        nameBtn    .setOnAction(e -> showSingleFieldPanel(contentArea, "New name:",       v -> user.setName(v)));
        usernameBtn.setOnAction(e -> showSingleFieldPanel(contentArea, "New username:",   v -> user.setUsername(v)));
        passwordBtn.setOnAction(e -> showPasswordPanel(contentArea, user));
        weightBtn  .setOnAction(e -> showWeightPanel(contentArea, user));
        heightBtn  .setOnAction(e -> showHeightPanel(contentArea, user));
        goalBtn    .setOnAction(e -> showGoalPanel(contentArea, user));
        activityBtn.setOnAction(e -> showActivityPanel(contentArea, user));
        backBtn    .setOnAction(e -> MainApp.showMain());

        VBox menu = new VBox(6,
                nameBtn, usernameBtn, passwordBtn, weightBtn,
                heightBtn, goalBtn, activityBtn, backBtn);

        view = new VBox(12, title, new Separator(), menu, contentArea);
        view.setPadding(new Insets(20));
    }

    // -------------------------------------------------------------------
    // Generic single text-field panel (used for name and username)
    // -------------------------------------------------------------------
    private void showSingleFieldPanel(VBox contentArea, String prompt,
                                      java.util.function.Consumer<String> setter) {
        Label label    = new Label(prompt);
        TextField field = new TextField();
        Button saveBtn  = new Button("Save");
        Label  msg      = new Label();

        saveBtn.setOnAction(e -> {
            String val = field.getText().trim();
            if (val.isEmpty()) {
                msg.setText("Field cannot be empty.");
                return;
            }
            setter.accept(val);
            msg.setStyle("-fx-text-fill: green;");
            msg.setText("Saved!");
            field.clear();
        });

        contentArea.getChildren().setAll(label, field, saveBtn, msg);
    }

    // -------------------------------------------------------------------
    // Password — uses a PasswordField so input is hidden
    // -------------------------------------------------------------------
    private void showPasswordPanel(VBox contentArea, User user) {
        Label         label = new Label("New password:");
        PasswordField field = new PasswordField();
        Button        saveBtn = new Button("Save");
        Label         msg     = new Label();

        saveBtn.setOnAction(e -> {
            String val = field.getText().trim();
            if (val.isEmpty()) {
                msg.setText("Password cannot be empty.");
                return;
            }
            user.setPassword(val);
            msg.setStyle("-fx-text-fill: green;");
            msg.setText("Saved!");
            field.clear();
        });

        contentArea.getChildren().setAll(label, field, saveBtn, msg);
    }

    // -------------------------------------------------------------------
    // Weight — numeric input, mirrors "Type in your new weight in kg"
    // -------------------------------------------------------------------
    private void showWeightPanel(VBox contentArea, User user) {
        Label     label   = new Label("New weight (kg):");
        TextField field   = new TextField(String.valueOf(user.getWeight()));
        Button    saveBtn = new Button("Save");
        Label     msg     = new Label();
        msg.setStyle("-fx-text-fill: red;");

        saveBtn.setOnAction(e -> {
            try {
                double val = Double.parseDouble(field.getText().trim());
                if (val <= 0) { msg.setText("Weight must be greater than 0."); return; }
                user.setWeight(val);
                msg.setStyle("-fx-text-fill: green;");
                msg.setText("Saved!");
            } catch (NumberFormatException ex) {
                msg.setText("Please enter a valid number.");
            }
        });

        contentArea.getChildren().setAll(label, field, saveBtn, msg);
    }

    // -------------------------------------------------------------------
    // Height — numeric input, mirrors "Type in your new height"
    // -------------------------------------------------------------------
    private void showHeightPanel(VBox contentArea, User user) {
        Label     label   = new Label("New height (cm):");
        TextField field   = new TextField(String.valueOf(user.getHeight()));
        Button    saveBtn = new Button("Save");
        Label     msg     = new Label();
        msg.setStyle("-fx-text-fill: red;");

        saveBtn.setOnAction(e -> {
            try {
                double val = Double.parseDouble(field.getText().trim());
                if (val <= 0) { msg.setText("Height must be greater than 0."); return; }
                user.setHeight(val);
                msg.setStyle("-fx-text-fill: green;");
                msg.setText("Saved!");
            } catch (NumberFormatException ex) {
                msg.setText("Please enter a valid number.");
            }
        });

        contentArea.getChildren().setAll(label, field, saveBtn, msg);
    }

    // -------------------------------------------------------------------
    // Goal — same three choices as getGoal() in UserInterface
    // -------------------------------------------------------------------
    private void showGoalPanel(VBox contentArea, User user) {
        Label title = new Label("Are you here to:");

        Button lossBtn     = new Button("(1) Lose weight");
        Button gainBtn     = new Button("(2) Gain weight");
        Button maintainBtn = new Button("(3) Maintain weight");
        Label  msg         = new Label();

        for (Button b : new Button[]{lossBtn, gainBtn, maintainBtn}) {
            b.setMaxWidth(Double.MAX_VALUE);
        }

        lossBtn    .setOnAction(e -> { user.setGoal("Loss");     setSuccess(msg, "Goal set to Loss."); });
        gainBtn    .setOnAction(e -> { user.setGoal("Gain");     setSuccess(msg, "Goal set to Gain."); });
        maintainBtn.setOnAction(e -> { user.setGoal("Maintain"); setSuccess(msg, "Goal set to Maintain."); });

        contentArea.getChildren().setAll(title, lossBtn, gainBtn, maintainBtn, msg);
    }

    // -------------------------------------------------------------------
    // Activity Level — same 1-5 options as getActivityLevel() in UserInterface
    // -------------------------------------------------------------------
    private void showActivityPanel(VBox contentArea, User user) {
        Label title = new Label("From 1-5, how active are you?");

        VBox btnBox = new VBox(6);
        Label msg   = new Label();

        for (int i = 1; i <= 5; i++) {
            final int level = i;
            Button btn = new Button("(" + i + ") Level " + i);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> {
                user.setActivityLevel(level);
                setSuccess(msg, "Activity level set to " + level + ".");
            });
            btnBox.getChildren().add(btn);
        }

        contentArea.getChildren().setAll(title, btnBox, msg);
    }

    private void setSuccess(Label msg, String text) {
        msg.setStyle("-fx-text-fill: green;");
        msg.setText(text);
    }

    public VBox getView() {
        return view;
    }
}