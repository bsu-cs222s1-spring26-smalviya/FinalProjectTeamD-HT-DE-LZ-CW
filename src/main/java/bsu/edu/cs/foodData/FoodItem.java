package bsu.edu.cs.foodData;

public class FoodItem {

    //Creating private variables so data won't conflict with other classes
    //For now hard coding for testing reasons
    private String foodName;
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

    public FoodItem(String foodName) {
        //For testing purposes, the food will always be rice...
        //Numbers will also be hard coded for now
        this.foodName = "rice";
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

    public String getFoodName() {
        return foodName;
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
