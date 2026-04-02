package bsu.edu.cs.FoodDataTests;

import bsu.edu.cs.foodData.FoodItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

    @Test
    public void testFoodItemConstructor() {
        FoodItem testApple = new FoodItem("rice");

        assertEquals("rice", testApple.getFoodName(), "The name should be 'rice'");

        assertEquals(130.0, testApple.getCalories(), "Hardcoded calories should be 130.0");
        assertEquals(2.7, testApple.getProtein(), "Hardcoded protein should be 2.7");
    }

    @Test
    public void testGetNutritionOutput() {
        FoodItem testBanana = new FoodItem("Banana");

        String expectedOutput = "rice\nCalories: 130.0\nProtein: 2.7g\nCarbs: 28.2g\nFat: 0.0g";

        assertEquals(expectedOutput, testBanana.getNutrition(), "The nutrition summary string is not correct.");
    }
}