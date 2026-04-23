package bsu.edu.cs.foodData;

import bsu.edu.cs.APIApps.USDAClient.USDAListParser;
import bsu.edu.cs.APIApps.USDAClient.USDAParser;

import bsu.edu.cs.APIApps.USDAClient.USDAToJsonClient;
import bsu.edu.cs.calculators.MacroCalc;
import org.json.JSONException;

import java.io.IOException;

public class FoodItem {
    private USDAParser parser;
    private String foodName;
    private int fdcID;
    private double baseWeight;
    private double calories;
    private double potassium;
    private double iron;
    private double satFat;
    private double unSatFat;
    private double protein;
    private double calcium;
    private double sugar;
    private double fiber;
    private double carbs;
    private double cholesterol;

    public FoodItem(int fdcID, USDAParser actualParser) throws IOException {

        parser = actualParser;
        USDAToJsonClient client = new USDAToJsonClient();
        client.getFoodItemJson(fdcID);
        try {
            this.foodName = parser.parseForName();
            this.fdcID = fdcID;
            this.baseWeight  = parser.parseWeightOfFood();
            this.calories = parser.parseForCalories();
            this.potassium = parser.parseForPotassium();
            this.iron = parser.parseForIron();
            this.satFat = parser.parseForSatFat();
            this.unSatFat = parser.parseForUnSatFat();
            this.protein = parser.parseForProtein();
            this.calcium = parser.parseForCalcium();
            this.sugar = parser.parseForSugar();
            this.fiber = parser.parseForFiber();
            this.carbs = parser.parseForCarbs();
            this.cholesterol = parser.parseForCholesterol();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to build FoodItem for fdcID: " + fdcID, e);
        }
    }

    public FoodItem(int fdcID) throws JSONException, IOException {
        this(fdcID, new USDAParser()); // This one fetches from API via USDAParser's default constructor
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

    public double getBaseWeight()  { return baseWeight; }

    public double getPotassium() {
        return potassium;
    }

    public double getIron() {
        return iron;
    }

    public double getUnSatFat() {
        return unSatFat;
    }

    public double getSatFat() { return satFat;}

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
        return foodName + "\nCalories: " + calories + "\nProtein: " + protein + "g\nCarbs: " + carbs
                + "g\nUnsaturated Fat: " + unSatFat + "g";
    }

    public void changeMacrosWithWeight(double weight){
        MacroCalc macroCalc = new MacroCalc();
        macroCalc.calcByWeight(this.baseWeight,weight);
        double ratio = macroCalc.getConversionRatio();
        this.calories*= ratio;
        this.protein*= ratio;
        this.carbs*= ratio;
        this.fiber*= ratio;
        this.sugar*= ratio;
        this.satFat*= ratio;
        this.unSatFat*= ratio;
        this.cholesterol *= ratio;
        this.potassium *= ratio;
        this.calcium *= ratio;
        this.iron *= ratio;
    }

    public String getName() {
        return foodName;
    }
}