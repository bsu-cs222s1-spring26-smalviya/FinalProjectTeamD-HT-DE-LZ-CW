package bsu.edu.cs.FoodDataTests;

import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.user.User;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataQueryTest {
    @Test
    public void testDisplayFoodData() throws JSONException {
        DataQuery dataQuery = new DataQuery("src/test/resources/TestHumbertoLogData.csv");
        String displayDataString = dataQuery.displayFoodData();
        String expectedDataString = "Calories: 130 kCal\n" +
                "Protein: 2.7 grams\n" +
                "Carbs: 28.2 grams\n" +
                "Total Fat: 0.5 grams\n" +
                "Saturated Fat: 0.1 grams\n" +
                "Fiber: 0.4 grams\n" +
                "Sugar: 0.1 grams\n" +
                "Cholesterol: 0.0 mg\n" +
                "Potassium: 35.0 mg\n" +
                "Calcium: 10.0 mg\n" +
                "Iron: 1.2 mg";
        assertEquals(expectedDataString,displayDataString);
    }
    @Test
    public void testGrabUserLogForDay() {
        DataQuery dataQuery = new DataQuery("src/test/resources/TestHumbertoLogData.csv");
        String dataForDay = dataQuery.grabUserLogForDay(12, 07, 25);
        String expectedString = "RED APPLES ORGANIC APPLE CHIPS, RED APPLES,\n130 calories,\n0 potassium,\n0 iron,\n0 fat,\n2.7 protein,\n0 calcium,\n0 sugar,\n0 fiber,\n28.2 carbs,\n0 cholesterol";
        assertEquals(expectedString, dataForDay);
    }

    @Test
    public void testGetUserLogString(){
        DataQuery dataQuery = new DataQuery(1);
        String expected = "src/main/resources/UserData/logs/1.csv";
        String actual = dataQuery.getUserLogString();
        Assertions.assertEquals(expected,actual);
    }
}
