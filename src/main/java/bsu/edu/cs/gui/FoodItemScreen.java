package bsu.edu.cs.gui;

import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.foodData.FoodItem;
import bsu.edu.cs.foodData.LogTime;
import bsu.edu.cs.user.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.json.JSONException;

import java.io.IOException;

public class FoodItemScreen {

    private VBox view;

    private Label caloriesLabel;
    private Label proteinLabel;
    private Label carbsLabel;
    private Label fiberLabel;
    private Label sugarLabel;
    private Label satFatLabel;
    private Label unSatFatLabel;
    private Label cholesterolLabel;
    private Label potassiumLabel;
    private Label calciumLabel;
    private Label ironLabel;

    public FoodItemScreen(FoodItem foodItem, DataQuery dataQuery) {
        User user = MainApp.getCurrentUser();

        Label titleLabel = new Label(foodItem.getName());
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label baseWeightNote = new Label(
                String.format("Base serving size: %.1f g", foodItem.getBaseWeight())
        );
        baseWeightNote.setStyle("-fx-text-fill: grey;");

        TextField weightField = new TextField(String.format("%.1f", foodItem.getBaseWeight()));
        weightField.setPrefWidth(90);

        Button calcBtn    = new Button("Calculate Weight");
        Label  weightError = new Label();
        weightError.setStyle("-fx-text-fill: red;");

        HBox weightRow = new HBox(10, new Label("Weight (g):"), weightField, calcBtn, weightError);
        weightRow.setAlignment(Pos.CENTER_LEFT);

        // --- Macro label grid ---
        caloriesLabel    = new Label();
        proteinLabel     = new Label();
        carbsLabel       = new Label();
        fiberLabel       = new Label();
        sugarLabel       = new Label();
        satFatLabel      = new Label();
        unSatFatLabel    = new Label();
        cholesterolLabel = new Label();
        potassiumLabel   = new Label();
        calciumLabel     = new Label();
        ironLabel        = new Label();

        // Populate labels at the base serving weight on load
        refreshMacroLabels(foodItem);

        GridPane macroGrid = buildMacroGrid();

        // --- Action buttons ---
        Button logBtn  = new Button("Log This Food");
        Button backBtn = new Button("Back to Search");
        Label  logMsg  = new Label();

        HBox buttonRow = new HBox(10, logBtn, backBtn);

        calcBtn.setOnAction(e -> {
            weightError.setText("");
            try {
                double newWeight = Double.parseDouble(weightField.getText().trim());
                if (newWeight <= 0) {
                    weightError.setText("Weight must be greater than 0.");
                    return;
                }
                foodItem.changeMacrosWithWeight(newWeight);
                refreshMacroLabels(foodItem);
            } catch (NumberFormatException ex) {
                weightError.setText("Please enter a valid number.");
            }
        });

        // --- Log: write the current (possibly scaled) macros to the user's log ---
        logBtn.setOnAction(e -> {
            LogTime timer = new LogTime();
            timer.parseTimeStringIntoVariables(timer.getCurrentTime());
            dataQuery.logFoodItem(
                    timer.getMonth(), timer.getDay(), timer.getYear(),
                    user.getWeight(), user.getWeightMeasurement()
            );
            logMsg.setText("Logged at: " + timer.makeTimeReadable());
            logBtn.setDisable(true); // prevent double-logging
        });

        backBtn.setOnAction(e -> MainApp.showFoodLog());

        // --- Assemble ---
        view = new VBox(12,
                titleLabel,
                baseWeightNote,
                new Separator(),
                weightRow,
                macroGrid,
                new Separator(),
                buttonRow,
                logMsg
        );
        view.setPadding(new Insets(20));
    }

    // Reads the current values straight off the (possibly mutated) FoodItem
    private void refreshMacroLabels(FoodItem item) {
        caloriesLabel.setText(String.format("%.1f kcal", item.getCalories()));
        proteinLabel.setText(String.format("%.2f g",    item.getProtein()));
        carbsLabel.setText(String.format("%.2f g",    item.getCarbs()));
        fiberLabel.setText(String.format("%.2f g",    item.getFiber()));
        sugarLabel.setText(String.format("%.2f g",    item.getSugar()));
        satFatLabel.setText(String.format("%.2f g",    item.getSatFat()));
        unSatFatLabel.setText(String.format("%.2f g",    item.getUnSatFat()));
        cholesterolLabel.setText(String.format("%.2f mg",   item.getCholesterol()));
        potassiumLabel.setText(String.format("%.2f mg",   item.getPotassium()));
        calciumLabel.setText(String.format("%.2f mg",   item.getCalcium()));
        ironLabel.setText(String.format("%.2f mg",   item.getIron()));
    }

    private GridPane buildMacroGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(8);

        String[] names = {
                "Calories", "Protein", "Carbs", "Fiber", "Sugar",
                "Saturated Fat", "Unsaturated Fat", "Cholesterol",
                "Potassium", "Calcium", "Iron"
        };
        Label[] values = {
                caloriesLabel, proteinLabel, carbsLabel, fiberLabel, sugarLabel,
                satFatLabel, unSatFatLabel, cholesterolLabel,
                potassiumLabel, calciumLabel, ironLabel
        };

        for (int i = 0; i < names.length; i++) {
            Label nameLabel = new Label(names[i] + ":");
            nameLabel.setStyle("-fx-font-weight: bold;");
            grid.add(nameLabel,  0, i);
            grid.add(values[i],  1, i);
        }

        return grid;
    }

    public VBox getView() {
        return view;
    }
}