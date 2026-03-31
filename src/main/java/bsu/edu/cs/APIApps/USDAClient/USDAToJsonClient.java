package bsu.edu.cs.APIApps.USDAClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.usda.invoker.ApiClient;
import com.usda.api.FdcApi;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class USDAToJsonClient {
    private final String apiKey;
    private final int pageSize = 5;
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
            Files.writeString(Path.of("itemQueryList.json"), json);
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
            Files.writeString(Path.of("itemInfo.json"), json);
        }
    }

    //This is just for when the user initially hits the search button in theory
    public String getURLStringForFoodList(String foodName){
        return getURLStringForFoodList(foodName, 1);
    }

    public String getURLStringForFoodList(String foodName,int pageNumber){
        String encodedTitle = URLEncoder.encode(foodName, java.nio.charset.StandardCharsets.UTF_8);
        String urlString = String.format(
                "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&query=%s&pageSize=%d&pageNumber=%d",
                apiKey,encodedTitle,pageSize,pageNumber);
        System.out.println(urlString);
        return urlString;
    }
    public String getURLStringForFoodID(int foodID){
        String foodName = Integer.toString(foodID);
        String encodedTitle = URLEncoder.encode(foodName, java.nio.charset.StandardCharsets.UTF_8);
        String urlString = String.format(
                "https://api.nal.usda.gov/fdc/v1/food/%s?api_key=%s",
                encodedTitle,apiKey);
        System.out.println(urlString);
        return urlString;
    }

}