package bsu.edu.cs.gui;

import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.foodData.LogTime;
import bsu.edu.cs.user.User;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.json.JSONException;

import java.io.IOException;

public class FoodInterfaceScreen {

    private VBox view;
    private DataQuery dataQuery;
    private User user;
    private String[] foodList = new String[5];
    public FoodInterfaceScreen() {
        user = MainApp.getCurrentUser();
        dataQuery = new DataQuery(user.getId());

        // --- Main menu buttons ---
        Button logFoodBtn = new Button("Log a New Food Item");
        Button viewLogBtn = new Button("View Today's Food Log");
        Button recipeBtn  = new Button("Search for Recipes");
        Button backBtn    = new Button("Return to Main Menu");

        // Content area that swaps based on selection
        VBox contentArea = new VBox(10);
        contentArea.setPadding(new Insets(10));

        logFoodBtn.setOnAction(e -> showLogFoodPanel(contentArea));
        viewLogBtn.setOnAction(e -> showTodayLogPanel(contentArea));
        recipeBtn.setOnAction(e -> {
            contentArea.getChildren().setAll(
                    new Label("[Recipe Search is currently under construction.]")
            );
        });
        backBtn.setOnAction(e -> MainApp.showMain());

        HBox menuBar = new HBox(10, logFoodBtn, viewLogBtn, recipeBtn, backBtn);
        menuBar.setPadding(new Insets(10));

        view = new VBox(10, menuBar, contentArea);
    }
    private void showLogFoodPanel(VBox contentArea) {
        TextField searchField = new TextField();
        searchField.setPromptText("Enter food name...");

        Button searchBtn = new Button("Search");
        Label statusLabel = new Label();

        // This will hold the list of results
        VBox resultsBox = new VBox(5);

        searchBtn.setOnAction(e -> {
            String foodName = searchField.getText().trim();
            if (foodName.isEmpty()) {
                statusLabel.setText("Please enter a food name.");
                return;
            }

            statusLabel.setText("Searching...");
            resultsBox.getChildren().clear();

            try {
                dataQuery.searchFoodList(foodName);
                int count = dataQuery.getNumberOfFoodOptions();

                if (count < 1) {
                    statusLabel.setText("No foods found under that name.");
                    return;
                }
                foodList = dataQuery.searchFoodList(foodName);
                statusLabel.setText("Select a food to log it:");

                for (int i = 1; i <= count; i++) {
                    final int choice = i;
                    // Build a label for this food option using searchFood result
                    // We re-use dataQuery's list parser indirectly via getFoodID
                    Button foodBtn = new Button(i + ": " + getFoodLabel(i));
                    foodBtn.setMaxWidth(Double.MAX_VALUE);

                    foodBtn.setOnAction(ev -> {
                        try {
                            int fdcID = dataQuery.getFoodID(choice);
                            dataQuery.setFoodItem(fdcID);

                            LogTime timer = new LogTime();
                            timer.parseTimeStringIntoVariables(timer.getCurrentTime());
                            dataQuery.logFoodItem(
                                    timer.getMonth(), timer.getDay(), timer.getYear(),
                                    user.getWeight(), user.getWeightMeasurement()
                            );

                            statusLabel.setText("Logged at: " + timer.makeTimeReadable());
                            resultsBox.getChildren().clear();
                            searchField.clear();
                        } catch (JSONException | IOException ex) {
                            statusLabel.setText("Error logging food: " + ex.getMessage());
                        }
                    });

                    resultsBox.getChildren().add(foodBtn);
                }

            } catch (JSONException | IOException ex) {
                statusLabel.setText("Search failed: " + ex.getMessage());
            }
        });

        HBox searchBar = new HBox(10, searchField, searchBtn);
        contentArea.getChildren().setAll(searchBar, statusLabel, resultsBox);
    }

    private String getFoodLabel(int position) {
        try {
            return "Food option " + foodList[position];
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private void showTodayLogPanel(VBox contentArea) {
        LogTime timer = new LogTime();
        timer.parseTimeStringIntoVariables(timer.getCurrentTime());

        String logData = dataQuery.grabUserLogForDay(
                timer.getMonth(), timer.getDay(), timer.getYear()
        );

        Label dateLabel = new Label("Log for " + timer.makeTimeReadable().substring(0, 10));
        dateLabel.setStyle("-fx-font-weight: bold;");

        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(300);
        logArea.setText(logData.isEmpty() ? "No entries logged today." : logData);

        contentArea.getChildren().setAll(dateLabel, logArea);
    }

    public VBox getView() {
        return view;
    }
}