package bsu.edu.cs.APIApps.USDAClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class USDAParser {

    private final JSONObject sourceJson;
    private final JSONArray nutrients;

    public USDAParser(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            sourceJson = new JSONObject(content);
            nutrients = sourceJson.getJSONArray("foodNutrients");
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
    public USDAParser(){
        this("src/main/resources/USDAData/itemInfo.json");
    }

    private double getNutrientAmount(int nutrientId) throws JSONException {
        String nutrientKey = "nutrient";
        String idKey = "id";
        String amountKey = "amount";
        double defaultAmount = 0.0;

        for (int position = 0; position < nutrients.length(); position++) {
            JSONObject entry = nutrients.getJSONObject(position);
            JSONObject nutrient = entry.getJSONObject(nutrientKey);
            if (nutrient.getInt(idKey) == nutrientId) {
                return entry.optDouble(amountKey, defaultAmount);
            }
        }
        return defaultAmount;
    }

    private double getNutrientAmountByName(String name) throws JSONException {
        for (int i = 0; i < nutrients.length(); i++) {
            JSONObject entry = nutrients.getJSONObject(i);
            JSONObject nutrient = entry.getJSONObject("nutrient");
            if (nutrient.getString("name").equalsIgnoreCase(name)) {
                return entry.optDouble("amount", 0.0);
            }
        }
        return 0.0;
    }

    public double parseWeightOfFood() throws JSONException {
        String foodPortionsKey = "foodPortions";
        String gramWeightKey = "gramWeight";
        int firstPortion = 0;

        return sourceJson.getJSONArray(foodPortionsKey)
                .getJSONObject(firstPortion)
                .getDouble(gramWeightKey);
    }

    public String parseForMeasurement() throws JSONException {
        String nutrientKey = "nutrient";
        String idKey = "id";
        String unitNameKey = "unitName";
        int waterNutrientId = 1051;
        String defaultUnit = "unknown";

        for (int position = 0; position < nutrients.length(); position++) {
            JSONObject entry = nutrients.getJSONObject(position);
            JSONObject nutrient = entry.getJSONObject(nutrientKey);
            if (nutrient.getInt(idKey) == waterNutrientId) {
                return nutrient.getString(unitNameKey);
            }
        }
        return defaultUnit;
    }

    public double parseForCalories() throws JSONException {
        for (int i = 0; i < nutrients.length(); i++) {
            JSONObject entry = nutrients.getJSONObject(i);
            JSONObject nutrient = entry.getJSONObject("nutrient");
            if (nutrient.getString("unitName").equals("kcal")) {
                return entry.optDouble("amount", 0.0);
            }
        }
        return 0.0;
    }

    public double parseForPotassium() throws JSONException {
        return getNutrientAmountByName("Potassium, K");
    }
    public double parseForIron() throws JSONException {
        return getNutrientAmountByName("Iron, Fe");
    }

    public double parseForSatFat() throws JSONException {
        return getNutrientAmountByName("Fatty acids, total saturated");
    }

    public double parseForUnSatFat() throws JSONException {
        double totalFat = getNutrientAmountByName("Total lipid (fat)");
        double satFat = getNutrientAmountByName("Fatty acids, total saturated");
        return Math.max(0.0, totalFat - satFat);
    }

    public double parseForProtein() throws JSONException {
        return getNutrientAmountByName("Protein");
    }

    public double parseForCalcium() throws JSONException {
        return getNutrientAmountByName("Calcium, Ca");
    }

    public double parseForSugar() throws JSONException {
        return getNutrientAmountByName("Total Sugars");
    }

    public double parseForFiber() throws JSONException {
        return getNutrientAmountByName("Fiber, total dietary");
    }

    public double parseForCarbs() throws JSONException {
        return getNutrientAmountByName("Carbohydrate, by difference");
    }

    public double parseForCholesterol() throws JSONException {
        return getNutrientAmountByName("Cholesterol");
    }

    public String parseForName() throws JSONException {
        String descriptionKey = "description";
        return sourceJson.getString(descriptionKey);
    }
}