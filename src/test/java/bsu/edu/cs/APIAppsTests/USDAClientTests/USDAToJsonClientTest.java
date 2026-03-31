package bsu.edu.cs.APIAppsTests.USDAClientTests;

import bsu.edu.cs.APIApps.USDAClient.USDAToJsonClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class USDAToJsonClientTest {
    //Example api url
    //https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&query=%s&pageSize=%d&pageNumber=%d
    @Test
    public void getURLStringForFoodListTest(){
        USDAToJsonClient usdaClient = new USDAToJsonClient();
        String urlResult = usdaClient.getURLStringForFoodList("apple");
        String yourAPIKey = System.getProperty("usdaKey");
        String expected = String.format("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&dataType=Branded" +
                "&query=apple&pageSize=5&pageNumber=1", yourAPIKey);
        Assertions.assertEquals(expected,urlResult);

    }

    @Test
    public void testGetURLString_withSpaces() {
        USDAToJsonClient usdaClient = new USDAToJsonClient();
        String urlResult = usdaClient.getURLStringForFoodList("red apple");
        String yourAPIKey = System.getProperty("usdaKey");
        String expected = String.format("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&dataType=Branded" +
                "&query=red+apple&pageSize=5&pageNumber=1", yourAPIKey);
        Assertions.assertEquals(expected,urlResult);
    }

    @Test
    public void testGetURLStringForAppleID(){
        USDAToJsonClient usdaClient = new USDAToJsonClient();
        String urlResult = usdaClient.getURLStringForFoodID(1750339);
        String yourAPIKey = System.getProperty("usdaKey");
        String expected = String.format("https://api.nal.usda.gov/fdc/v1/food/1750339?" +
                "api_key=%s&format=abridged", yourAPIKey);
        Assertions.assertEquals(expected,urlResult);
    }
}