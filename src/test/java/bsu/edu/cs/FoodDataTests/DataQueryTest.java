package bsu.edu.cs.FoodDataTests;

import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataQueryTest {
    @Test
    public void testDisplayFoodData(){
        DataQuery dataQuery = new DataQuery();
        String foodName = "rice";
        String displayDataString = dataQuery.displayFoodData("rice");
        String expectedDataString = "Calories: 130 kCal\n" +
                "Protein: 2.7 grams\nCarbs: 28.2 grams";
        assertEquals(expectedDataString,displayDataString);
    }
    @Test
    public void testGrabUserLogForDay(){
        DataQuery dataQuery = new DataQuery();
        String dataForDay = dataQuery.grabUserLogForDay(12,07,25);
        String expectedString = "Rice,\n 130 calories,\n0 postassium,\n0 iron,\n0 fat,\n2.7 protein,\n0 calcium,\n0 sugar,\n0 fiber,\n28.2 carbs,\n0 cholesterol";
        assertEquals(expectedString,dataForDay);
    }

}
