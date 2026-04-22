package bsu.edu.cs.APIApps.USDAClient;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

//The reason we save these to a json file is to be able to grab any older data in case the user wants to
//come back later to look at a different item in the same list and that...
public class USDAToJsonClient {
    private final String apiKey;
    private final int pageSize = 5;
    private final String[] dataType = new String[]{"Branded"};
    //There are [Branded,FNDDS,SR Legacy,Experimental Foods,Foundation Foods]
    private final String format = "full";
    private String foodItemJsonFile = "src/main/resources/USDAData/itemInfo.json";
    private String listOfFoodsJsonFile = "src/main/resources/USDAData/itemQueryList.json";
    public USDAToJsonClient() {
         apiKey = System.getProperty("usdaKey");
    }

    public void getFoodListJson(String foodName) throws IOException, JSONException {
        String urlString = getURLStringForFoodList(foodName);
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "WorkItOut; HumbertoTorres(humberto.torres@bsu.edu)");
        try (InputStream inputStream = connection.getInputStream()) {
            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            // Write to itemQueryList.json
            Files.writeString(Path.of(listOfFoodsJsonFile), json);
        }
    }

    public void getFoodItemJson(int foodID) throws IOException {
        String urlString = getURLStringForFoodID(foodID);
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "WorkItOut; HumbertoTorres(humberto.torres@bsu.edu)");
        try (InputStream inputStream = connection.getInputStream()) {
            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            // Write to itemInfo.json
            Files.writeString(Path.of(foodItemJsonFile), json);
        }
    }

    //This is just for when the user initially hits the search button in theory
    public String getURLStringForFoodList(String foodName){
        return getURLStringForFoodList(foodName, 1);
    }

    public String getURLStringForFoodList(String foodName,int pageNumber){
        String encodedTitle = URLEncoder.encode(foodName, java.nio.charset.StandardCharsets.UTF_8);
        String joinedData = String.join(",", dataType);
        String urlString = String.format(
                "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&dataType=%s&query=%s&pageSize=%d&pageNumber=%d",
                apiKey,joinedData,encodedTitle,pageSize,pageNumber);
//        System.out.println(urlString);
        return urlString;
    }
    public String getURLStringForFoodID(int foodID){
        String foodName = Integer.toString(foodID);
        String encodedTitle = URLEncoder.encode(foodName, java.nio.charset.StandardCharsets.UTF_8);
        String urlString = String.format(
                "https://api.nal.usda.gov/fdc/v1/food/%s?api_key=%s&format=%s",
                encodedTitle,apiKey,format);
//        System.out.println(urlString);
        return urlString;
    }

    public void setFoodItemJsonFile(String foodItemJsonFile) {
        this.foodItemJsonFile = foodItemJsonFile;
    }

    public void setListOfFoodsJsonFile(String listOfFoodsJsonFile) {
        this.listOfFoodsJsonFile = listOfFoodsJsonFile;
    }
}