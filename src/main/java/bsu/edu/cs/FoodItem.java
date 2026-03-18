package bsu.edu.cs;

public class FoodItem {

    //Creating private variables so data won't conflict with other classes
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
        this.foodName = foodName;
        this.calories = 0.0;
        this.potassium = 0.0;
        this.iron = 0.0;
        this.fat = 0.0;
        this.protein = 0.0;
        this.calcium = 0.0;
        this.sugar = 0.0;
        this.fiber = 0.0;
        this.carbs = 0.0;
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
