package bsu.edu.cs.APIAppsTests.USDAClientTests;

import bsu.edu.cs.APIApps.USDAClient.USDAToJsonClient;
import bsu.edu.cs.foodData.DataQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class USDAToJsonClientTest {
    //Example api url
    //https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&query=%s&pageSize=%d&pageNumber=%d
    @Test
    public void getURLStringForFoodListTest(){
        USDAToJsonClient usdaClient = new USDAToJsonClient();
        String urlResult = usdaClient.getURLStringForFoodList("apple");
        String yourAPIKey = System.getProperty("usdaKey");
        String expected = String.format("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&query=apple" +
                        "&pageSize=1&pageNumber=1", yourAPIKey);
        Assertions.assertEquals(expected,urlResult);

    }

    @Test
    public void testGetURLString_withSpaces() {
        USDAToJsonClient usdaClient = new USDAToJsonClient();
        String urlResult = usdaClient.getURLStringForFoodList("red apple");
        String yourAPIKey = System.getProperty("usdaKey");
        String expected = String.format("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=%s&query=red+apple" +
                "&pageSize=1&pageNumber=1", yourAPIKey);
        Assertions.assertEquals(expected,urlResult);
    }
}