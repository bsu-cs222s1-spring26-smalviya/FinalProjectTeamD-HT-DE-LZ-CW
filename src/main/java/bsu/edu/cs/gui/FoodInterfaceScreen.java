package bsu.edu.cs.gui;

import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.foodData.FoodItem;
import bsu.edu.cs.foodData.LogTime;
import bsu.edu.cs.user.User;
import bsu.edu.cs.calculators.WorkItCalc;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

public class FoodInterfaceScreen {

    private VBox view;
    private DataQuery dataQuery;
    private User user;

    public FoodInterfaceScreen() {
        user      = MainApp.getCurrentUser();
        dataQuery = new DataQuery(user.getId());

        // --- Top menu ---
        Button logFoodBtn = new Button("Log a New Food Item");
        Button viewLogBtn = new Button("View Today's Food Log");
        Button recipeBtn  = new Button("Search for Recipes");
        Button backBtn    = new Button("Return to Main Menu");

        logFoodBtn.setMaxWidth(Double.MAX_VALUE);
        viewLogBtn.setMaxWidth(Double.MAX_VALUE);
        recipeBtn.setMaxWidth(Double.MAX_VALUE);
        backBtn.setMaxWidth(Double.MAX_VALUE);

        VBox contentArea = new VBox(10);
        contentArea.setPadding(new Insets(10));

        logFoodBtn.setOnAction(e -> showLogFoodPanel(contentArea));
        viewLogBtn.setOnAction(e -> showTodayLogPanel(contentArea));
        recipeBtn.setOnAction(e ->
                contentArea.getChildren().setAll(
                        new Label("[Recipe Search is currently under construction.]")
                )
        );
        backBtn.setOnAction(e -> MainApp.showMain());

        HBox menuBar = new HBox(10, logFoodBtn, viewLogBtn, recipeBtn, backBtn);
        menuBar.setPadding(new Insets(10));

        view = new VBox(10, menuBar, new Separator(), contentArea);
        view.setPadding(new Insets(10));
    }

    private void showLogFoodPanel(VBox contentArea) {
        TextField searchField  = new TextField();
        searchField.setPromptText("Enter food name...");

        Button searchBtn   = new Button("Search");
        Label  statusLabel = new Label();
        VBox   resultsBox  = new VBox(5);

        searchBtn.setOnAction(e -> {
            String foodName = searchField.getText().trim();
            if (foodName.isEmpty()) {
                statusLabel.setText("Please enter a food name.");
                return;
            }

            statusLabel.setText("Searching...");
            resultsBox.getChildren().clear();
            searchBtn.setDisable(true);

            // Run the network search on a background thread
            Task<Void> searchTask = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    dataQuery.searchFood(foodName);
                    return null;
                }
            };

            searchTask.setOnSucceeded(ev -> {
                searchBtn.setDisable(false);
                int count = dataQuery.getNumberOfFoodOptions();

                if (count < 1) {
                    statusLabel.setText("No foods found under that name.");
                    return;
                }

                statusLabel.setText("Select a food to view its nutrition info:");

                for (int i = 1; i <= count; i++) {
                    final int choice = i;
                    try {
                        String label = dataQuery.getFoodLabel(choice);
                        Button foodBtn = new Button(label);
                        foodBtn.setMaxWidth(Double.MAX_VALUE);

                        foodBtn.setOnAction(ev2 -> loadFoodItem(choice, statusLabel, foodBtn));

                        resultsBox.getChildren().add(foodBtn);
                    } catch (JSONException ex) {
                        statusLabel.setText("Error reading result " + i);
                    }
                }
            });

            searchTask.setOnFailed(ev -> {
                searchBtn.setDisable(false);
                statusLabel.setText("Search failed: " + searchTask.getException().getMessage());
            });

            new Thread(searchTask).start();
        });

        HBox searchBar = new HBox(10, searchField, searchBtn);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        contentArea.getChildren().setAll(searchBar, statusLabel, resultsBox);
    }

    private void loadFoodItem(int choice, Label statusLabel, Button clickedBtn) {
        statusLabel.setText("Loading nutrition data...");
        clickedBtn.setDisable(true);

        Task<Void> loadTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                int fdcID = dataQuery.getFoodID(choice);
                dataQuery.setFoodItem(fdcID);   // ← network call, safe on background thread
                return null;
            }
        };

        loadTask.setOnSucceeded(ev ->
                // Back on the JavaFX thread for UI navigation
                MainApp.showFoodItem(dataQuery.getFoodItem(), dataQuery)
        );

        loadTask.setOnFailed(ev -> {
            clickedBtn.setDisable(false);
            statusLabel.setText("Error loading food: " + loadTask.getException().getMessage());
        });

        new Thread(loadTask).start();
    }
    private void showTodayLogPanel(VBox contentArea) {
        LogTime timer = new LogTime();
        timer.parseTimeStringIntoVariables(timer.getCurrentTime());

        PieChart calorieChart = buildCalorieChart(timer.getMonth(), timer.getDay(), timer.getYear());

        LineChart<String, Number> weightChart = buildWeightChart();

        Label todayLabel = new Label(
                "Log for " + timer.makeTimeReadable().substring(0, 10)
        );
        todayLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");

        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(200);
        loadDayIntoArea(logArea, timer.getMonth(), timer.getDay(), timer.getYear());

        Label lookupLabel = new Label("Looking for a specific day?");
        lookupLabel.setStyle("-fx-font-style: italic;");

        TextField monthField = new TextField();
        monthField.setPromptText("Month");
        monthField.setPrefWidth(70);

        TextField dayField = new TextField();
        dayField.setPromptText("Day");
        dayField.setPrefWidth(70);

        TextField yearField = new TextField();
        yearField.setPromptText("Year");
        yearField.setPrefWidth(80);

        Button lookupBtn = new Button("View Log");
        Label  lookupMsg = new Label();
        lookupMsg.setStyle("-fx-text-fill: red;");

        lookupBtn.setOnAction(e -> {
            lookupMsg.setText("");
            try {
                int m = Integer.parseInt(monthField.getText().trim());
                int d = Integer.parseInt(dayField.getText().trim());
                int y = Integer.parseInt(yearField.getText().trim());

                if (m < 1 || m > 12 || d < 1 || d > 31) {
                    lookupMsg.setText("Please enter a valid date.");
                    return;
                }

                todayLabel.setText(String.format("Log for %02d/%02d/%04d", m, d, y));
                loadDayIntoArea(logArea, m, d, y);

                // Refresh pie chart for the looked-up day
                PieChart updated = buildCalorieChart(m, d, y);
                contentArea.getChildren().set(0, updated);
            } catch (NumberFormatException ex) {
                lookupMsg.setText("Month, day, and year must be numbers.");
            }
        });

        HBox dateRow = new HBox(8, monthField, dayField, yearField, lookupBtn, lookupMsg);
        dateRow.setAlignment(Pos.CENTER_LEFT);

        VBox lookupBox = new VBox(6, lookupLabel, dateRow);
        lookupBox.setPadding(new Insets(10, 0, 0, 0));

        contentArea.getChildren().setAll(
                calorieChart,
                weightChart,
                new Separator(),
                todayLabel,
                logArea,
                new Separator(),
                lookupBox
        );
    }

    private PieChart buildCalorieChart(int month, int day, int year) {
        double consumed = dataQuery.getTotalCaloriesForDay(month, day, year);

        WorkItCalc calc = new WorkItCalc();
        int limit = calc.CalculateCaloricNeeds(
                user.getWeight(),
                user.getHeight(),
                25, // age not stored — using 25 as default
                user.getGoal().toUpperCase(),
                user.getActivityLevel(),
                user.getGender().toUpperCase()
        );

        double remaining = Math.max(0, limit - consumed);

        PieChart chart = new PieChart();
        chart.setTitle(String.format("Calories: %.0f / %d kcal", consumed, limit));
        chart.setPrefHeight(220);
        chart.setLabelsVisible(true);
        chart.setLegendVisible(true);

        PieChart.Data consumedSlice  = new PieChart.Data(String.format("Consumed (%.0f)", consumed), consumed);
        PieChart.Data remainingSlice = new PieChart.Data(String.format("Remaining (%.0f)", remaining), remaining > 0 ? remaining : 0);

        if (consumed <= 0 && remaining <= 0) {
            chart.getData().add(new PieChart.Data("No data", 1));
        } else if (consumed <= 0) {
            chart.getData().add(new PieChart.Data(String.format("Remaining (%d)", limit), limit));
        } else {
            chart.getData().addAll(consumedSlice, remainingSlice);
        }

        return chart;
    }

    private LineChart<String, Number> buildWeightChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Weight (kg)");

        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Weight Over Time");
        chart.setLegendVisible(false);
        chart.setPrefHeight(220);
        chart.setCreateSymbols(true);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        Map<String, Double> entries = dataQuery.getAllWeightEntries();
        if (entries.isEmpty()) {
            chart.setTitle("Weight Over Time (no data yet)");
        } else {
            for (Map.Entry<String, Double> entry : entries.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
        }

        chart.getData().add(series);
        return chart;
    }

    // Populates a TextArea with the log for a given day, or a "no entries" message
    private void loadDayIntoArea(TextArea area, int month, int day, int year) {
        String logData = dataQuery.grabUserLogForDay(month, day, year);
        area.setText(logData.isEmpty() ? "No entries logged for this day." : logData);
    }

    public VBox getView() {
        return view;
    }
}