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
        int energyNutrientId = 2047;
        return getNutrientAmount(energyNutrientId);
    }

    public double parseForPotassium() throws JSONException {
        int potassiumNutrientId = 1092;
        return getNutrientAmount(potassiumNutrientId);
    }

    public double parseForIron() throws JSONException {
        int ironNutrientId = 1089;
        return getNutrientAmount(ironNutrientId);
    }

    public double parseForSatFat() throws JSONException {
        int satFatNutrientId = 1258;
        return getNutrientAmount(satFatNutrientId);
    }

    public double parseForUnSatFat() throws JSONException {
        int unSatFatNutrientId = 1004;
        return getNutrientAmount(unSatFatNutrientId);
    }

    public double parseForProtein() throws JSONException {
        int proteinNutrientId = 1003;
        return getNutrientAmount(proteinNutrientId);
    }

    public double parseForCalcium() throws JSONException {
        int calciumNutrientId = 1087;
        return getNutrientAmount(calciumNutrientId);
    }

    public double parseForSugar() throws JSONException {
        int sugarNutrientId = 2000;
        return getNutrientAmount(sugarNutrientId);
    }

    public double parseForFiber() throws JSONException {
        int fiberNutrientId = 1079;
        return getNutrientAmount(fiberNutrientId);
    }

    public double parseForCarbs() throws JSONException {
        int carbsNutrientId = 1005;
        return getNutrientAmount(carbsNutrientId);
    }

    public double parseForCholesterol() throws JSONException {
        int cholesterolNutrientId = 1253;
        return getNutrientAmount(cholesterolNutrientId);
    }
    public String parseForName() throws JSONException {
        String descriptionKey = "description";
        return sourceJson.getString(descriptionKey);
    }
}