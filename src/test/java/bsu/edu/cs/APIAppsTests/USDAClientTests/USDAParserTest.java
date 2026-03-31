package bsu.edu.cs.APIAppsTests.USDAClientTests;

import bsu.edu.cs.APIApps.USDAClient.USDAParser;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class USDAParserTest {
    private static final String testingFile = "src/test/resources/riceNutrition.json";

    @Test
    public void parseForWeightOfFoodTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actualWeight = parser.parseWeightOfFood();
        double expectedWeight = 140;
        Assertions.assertEquals(expectedWeight,actualWeight);
    }
    @Test
    public void parseForWeightMeasurement() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        String actualMeasurement = parser.parseForMeasurement();
        String expectedMeasurement = "g";
        Assertions.assertEquals(expectedMeasurement, actualMeasurement);
    }
    @Test
    public void parseForCaloriesTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForCalories();
        double expected = 61.7893;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForPotassiumTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForPotassium();
        double expected = 95.31;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void parseForIronTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForIron();
        double expected = 0.0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForSatFatTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForSatFat();
        double expected = 0.0; // not present in this food's data
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForUnSatFatTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForUnSatFat();
        double expected = 0.2125;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForProteinTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForProtein();
        double expected = 0.1875;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForCalciumTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForCalcium();
        double expected = 4.656;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForSugarTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForSugar();
        double expected = 12.22;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForFiberTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForFiber();
        double expected = 2.043;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForCarbsTest() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForCarbs();
        double expected = 14.7817;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void parseForCholesterol() throws JSONException {
        USDAParser parser = new USDAParser(testingFile);
        double actual = parser.parseForCholesterol();
        double expected = 0.0; // not present in this food's data
        Assertions.assertEquals(expected, actual);
    }
}
