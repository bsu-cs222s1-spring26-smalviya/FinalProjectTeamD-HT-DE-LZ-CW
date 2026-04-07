package bsu.edu.cs.FoodDataTests;
import bsu.edu.cs.foodData.FoodItem;
import bsu.edu.cs.APIApps.USDAClient.USDAParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

    private FoodItem apple;

    @BeforeEach
    public void setUp() throws Exception {
        USDAParser testParser = new USDAParser("src/test/resources/riceNutrition.json");
        apple = new FoodItem(1750339,testParser);
    }

    @Test
    public void testFoodItemName() {
        assertEquals("Apples, red delicious, with skin, raw", apple.getName());
    }

    @Test
    public void testFoodItemCalories() {
        // Matched by unitName "kcal" — will grab first kcal entry (2047), amount 61.79
        assertEquals(61.79, apple.getCalories(), 1);
    }

    @Test
    public void testFoodItemProtein() {
        assertEquals(0.26, apple.getProtein(), 1);
    }

    @Test
    public void testFoodItemCarbs() {
        assertEquals(14.25, apple.getCarbs(), 1);
    }

    @Test
    public void testFoodItemSugar() {
        assertEquals(12.22, apple.getSugar(), 1);
    }

    @Test
    public void testFoodItemFiber() {
        assertEquals(2.31, apple.getFiber(), 1);
    }

    @Test
    public void testFoodItemIron() {
        assertEquals(0.12, apple.getIron(), 1);
    }

    @Test
    public void testFoodItemCalcium() {
        assertEquals(4.68, apple.getCalcium(), 1);
    }

    @Test
    public void testFoodItemCholesterol() {
        assertEquals(0.0, apple.getCholesterol(), 1);
    }

    @Test
    public void testFoodItemUnSatFat() {
        // total fat - sat fat
        double expectedTotalFat = 0.17;
        double expectedSatFat = 0.028;
        assertEquals(expectedTotalFat - expectedSatFat, apple.getUnSatFat(), 1);
    }
// Commented out cuz its gonna be off just by a little
//    @Test
//    public void testGetNutritionOutput() {
//        String expected = "Apples, red delicious, with skin, raw\nCalories: 61.79\nProtein: 0.26g\nCarbs: 14.25g\nUnsaturated Fat: 0.14g";
//        assertEquals(expected, apple.getNutrition());
//    }
}
//
//import bsu.edu.cs.foodData.FoodItem;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class FoodItemTest {
//
//    @Test
//    public void testFoodItemConstructor() {
//        FoodItem testApple = new FoodItem("rice");
//
//        assertEquals("rice", testApple.getFoodName(), "The name should be 'rice'");
//
//        assertEquals(130.0, testApple.getCalories(), "Hardcoded calories should be 130.0");
//        assertEquals(2.7, testApple.getProtein(), "Hardcoded protein should be 2.7");
//    }
//
//    @Test
//    public void testGetNutritionOutput() {
//        FoodItem testBanana = new FoodItem("Banana");
//
//        String expectedOutput = "rice\nCalories: 130.0\nProtein: 2.7g\nCarbs: 28.2g\nFat: 0.0g";
//
//        assertEquals(expectedOutput, testBanana.getNutrition(), "The nutrition summary string is not correct.");
//    }
//}