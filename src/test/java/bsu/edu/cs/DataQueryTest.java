package bsu.edu.cs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataQueryTest {
    @Test
    public void testDisplayData(){
        DataQuery dataQuery = new DataQuery();
        String displayDataString = dataQuery.displayData();
        String expectedDataString = "Calories: 130 kCal\n" +
                "Protein: 2.7 grams\nCarbs: 28.2 grams";
        assertEquals(expectedDataString,displayDataString);
    }
    @Test
    public void testGrabDataFromFiles() {
        DataQuery dataQuery = new DataQuery();
        User testUser = dataQuery.grabDataFromFiles("humTor","shrimp");
        User expectedUser = new User("humberto",220,180,"loss",1900);
        assertEquals(expectedUser,testUser);
    }
    @Test
    public void testGrabUserLogForDay(){
        DataQuery dataQuery = new DataQuery();
        String dataForDay = dataQuery.grabUserLogForDay(12,07,25);
        String expectedString = "Rice\n,\n 130 calories,\n0 postassium,\n0 iron,\n0 fat,\n2.7 protein,\n0 calcium,\n0 sugar,\n0 fiber,\n28.2 carbs,\n0 cholesterol"
        assertEquals(expectedString,dataForDay);
    }

}
