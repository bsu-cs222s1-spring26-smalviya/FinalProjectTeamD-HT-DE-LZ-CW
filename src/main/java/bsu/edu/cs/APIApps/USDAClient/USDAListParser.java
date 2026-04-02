package bsu.edu.cs.APIApps.USDAClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class USDAListParser {
    private static JSONObject fileObj;

    public USDAListParser(String file) {
        try {
            String content = Files.readString(Path.of(file));
            fileObj = new JSONObject(content);
        } catch (IOException | JSONException error) {
            throw new RuntimeException("Failed to read JSON file: " + file, error);
        }
    }
    public USDAListParser(){
        this("src/main/resources/USDAData/itemQueryList.json");
    }
    public void searchForFoods(String foodName) throws JSONException, IOException {
        USDAToJsonClient client = new USDAToJsonClient();
        client.getFoodListJson(foodName);
    }

    public int parseForNumberOfPages() throws JSONException {
        return fileObj.getJSONArray("pageList").length();
    }

    public int parseForFDCID(int position) throws JSONException {
        return fileObj.getJSONArray("foods").getJSONObject(position - 1).getInt("fdcId");
    }

    public String parseForNameofFood(int position) throws JSONException {
        return fileObj.getJSONArray("foods").getJSONObject(position - 1).getString("description");
    }

    public int parseForCurrentPage() throws JSONException {
        return fileObj.getInt("currentPage");

    }

    public int parseForLastPage() throws JSONException {
        JSONArray pageList = fileObj.getJSONArray("pageList");
        return pageList.getInt(pageList.length() - 1);
    }

    public String parseForBrandNameOfFood(int position) throws JSONException {
        return fileObj.getJSONArray("foods").getJSONObject(position - 1).getString("brandName");
    }

    public double parseForCaloriesOfFood(int position) throws JSONException {
        JSONArray nutrients = fileObj.getJSONArray("foods").getJSONObject(position - 1)
                .getJSONArray("foodNutrients");
        for (int parseNutrients = 0; parseNutrients < nutrients.length(); parseNutrients++) {
            JSONObject nutrient = nutrients.getJSONObject(parseNutrients);
            if (nutrient.getInt("nutrientId") == 1008) {
                return nutrient.getDouble("value");
            }
        }
        return 0.0;
    }
}
