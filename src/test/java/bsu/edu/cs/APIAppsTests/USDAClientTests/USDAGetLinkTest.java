package bsu.edu.cs.APIAppsTests.USDAClientTests;

import bsu.edu.cs.APIApps.USDAClient.USDAGetLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class USDAGetLinkTest {
    private static final String usdaAPIKey = System.getProperty("usdaKey","DEMO_KEY");

    @Test
    public void testGetFoodListSearchLink(){
        USDAGetLink usdaGetLink = new USDAGetLink();
        String actualLink = usdaGetLink.getFoodListLink("cheddar cheese");
        String expectedLink = String.format("https://api.nal.usda.gov/fdc/v1/foods/search?" +
                "api_key=" + usdaAPIKey +"&query=cheddar%20cheese&pageSize=15&pageNumber=1"
        );
        Assertions.assertEquals(expectedLink,actualLink);
    }
    @Test
    public void testGetFoodSpecificSearchLink(){
        USDAGetLink usdaGetLink = new USDAGetLink();
        String cheddarCheeseFoodID = "45001529";
        String actualLink = usdaGetLink.getFoodNutritionLink(cheddarCheeseFoodID);
        String expectedLink = String.format("https://api.nal.usda.gov/fdc/v1/food/%s?api_key=%s",cheddarCheeseFoodID,usdaAPIKey);
        Assertions.assertEquals(expectedLink,actualLink);
    }
}
