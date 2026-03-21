package bsu.edu.cs;

import bsu.edu.cs.foodData.FoodItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

    @Test
    public void testFoodItemConstructor() {
        FoodItem testApple = new FoodItem("rice");

        assertEquals("rice", testApple.getFoodName(), "The name should be 'rice'");

        assertEquals(0.0, testApple.getCalories(), "Default calories should be 0.0");
        assertEquals(0.0, testApple.getProtein(), "Default protein should be 0.0");
    }

    @Test
    public void testGetNutritionOutput() {
        FoodItem testBanana = new FoodItem("Banana");

        String expectedOutput = "Banana\nCalories: 0.0\nProtein: 0.0g\nCarbs: 0.0g\nFat: 0.0g";

        assertEquals(expectedOutput, testBanana.getNutrition(), "The nutrition summary string is not correct.");
    }
}