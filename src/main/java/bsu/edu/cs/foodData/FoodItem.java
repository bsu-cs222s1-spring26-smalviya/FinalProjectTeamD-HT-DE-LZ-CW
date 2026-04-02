package bsu.edu.cs.foodData;

import bsu.edu.cs.APIApps.USDAClient.USDAParser;
import bsu.edu.cs.APIApps.USDAClient.USDAListParser;

public class FoodItem {

    private USDAParser parser = new USDAParser();
    private USDAListParser listParser = new USDAListParser();
    private String foodName;
    private int fdcID;
    private double calories;
    private double potassium;
    private double iron;
    private double fat;
    private double protein;
    private double calcium;
    private double sugar;
    private double fiber;
    private double carbs;
    private double cholesterol;

    public FoodItem() {
        try {
            this.foodName = listParser.parseForNameofFood(1);
            this.fdcID = listParser.parseForFDCID(1);
            this.calories = parser.parseForCalories();
            this.potassium = parser.parseForPotassium();
            this.iron = parser.parseForIron();
            this.fat = parser.parseForSatFat() + parser.parseForUnSatFat();
            this.protein = parser.parseForProtein();
            this.calcium = parser.parseForCalcium();
            this.sugar = parser.parseForSugar();
            this.fiber = parser.parseForFiber();
            this.carbs = parser.parseForCarbs();
            this.cholesterol = parser.parseForCholesterol();
        } catch (Exception e) {
            this.foodName = "Rice";
            this.fdcID = 0;
            this.calories = 130.0;
            this.potassium = 0.0;
            this.iron = 0.0;
            this.fat = 0.0;
            this.protein = 2.7;
            this.calcium = 0.0;
            this.sugar = 0.0;
            this.fiber = 0.0;
            this.carbs = 28.2;
            this.cholesterol = 0.0;
        }
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFdcID() {
        return fdcID;
    }

    public double getCalories() {
        return calories;
    }

    public double getPotassium() {
        return potassium;
    }

    public double getIron() {
        return iron;
    }

    public double getFat() {
        return fat;
    }

    public double getProtein() {
        return protein;
    }

    public double getCalcium() {
        return calcium;
    }

    public double getSugar() {
        return sugar;
    }

    public double getFiber() {
        return fiber;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public String getNutrition() {
        return foodName + "\nCalories: " + calories + "\nProtein: " + protein + "g\nCarbs: " + carbs + "g\nFat: " + fat + "g";
    }
}