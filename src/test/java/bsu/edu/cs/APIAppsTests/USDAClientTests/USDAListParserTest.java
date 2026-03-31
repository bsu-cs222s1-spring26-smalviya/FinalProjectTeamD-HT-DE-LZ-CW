package bsu.edu.cs.APIAppsTests.USDAClientTests;

import bsu.edu.cs.APIApps.USDAClient.USDAListParser;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class USDAListParserTest {
    @Test
    public void testParseForNumberOfPages(){
        USDAListParser listParser = new USDAListParser("src/test/resources/red+applesList.json");
        int actual = listParser.parseForNumberOfPages();
        int expectedInt = 5;
        Assertions.assertEquals(expectedInt,actual);
    }

    @Test
    public void testParseForFDCID_firstInList(){
        USDAListParser listParser = new USDAListParser("src/test/resources/red+applesList.json");
        int expectedID = 1750339;
        int acutal = listParser.parseForFDCID();
        Assertions.assertEquals(expectedID,acutal);
    }

    //All the rest testing the third in the list on the first page
    //Info commented in the last part of the page

    @Test
    public void testParseForNameOfFood(){
        USDAListParser listParser = new USDAListParser("src/test/resources/red+applesList.json");
        String actual =  listParser.parseForNameofFood(3);
        String expected = "RED APPLES ORGANIC APPLE CHIPS";
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testParseForCurrentPage(){
        USDAListParser listParser = new USDAListParser("src/test/resources/red+applesList.json");
        int expected = 1;
        int actual = listParser.parseForCurrentPage();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testParseForLastPage(){
        USDAListParser listParser = new USDAListParser("src/test/resources/red+applesList.json");
        int expected = 10;
        int actual = listParser.parseForLastPage();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testParseForBrandNameOfFood(){
        USDAListParser listParser = new USDAListParser("src/test/resources/red+applesList.json");
        String expected = "GOURMET NUT.";
        String actual = listParser.parseForBrandNameOfFood(3);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testParseForCaloriesOfFood(){
        USDAListParser listParser = new USDAListParser("src/test/resources/red+applesList.json");
        double expected = 381;
        double actual = listParser.parseForCaloriesOfFood(3);
    }
}
//"fdcId": 2422644,
//        "description": "RED APPLES ORGANIC APPLE CHIPS, RED APPLES",
//        "dataType": "Branded",
//        "gtinUpc": "857468006279",
//        "publishedDate": "2022-12-22",
//        "brandOwner": "Gourmet Nut",
//        "brandName": "GOURMET NUT.",
//        "subbrandName": "SIMPLE SLICES",
//        "ingredients": "ORGANIC APPLES.",
//        "marketCountry": "United States",
//        "foodCategory": "Wholesome Snacks",
//        "modifiedDate": "2021-10-11",
//        "dataSource": "LI",
//        "packageWeight": "6 oz/170 g",
//        "servingSizeUnit": "g",
//        "servingSize": 21,
//        "householdServingFullText": "1 pouch",
//        "shortDescription": "",
//        "tradeChannels": [
//        "NO_TRADE_CHANNEL"
//        ],
//        "allHighlightFields": "\u003Cb\u003EIngredients\u003C/b\u003E: ORGANIC \u003Cem\u003EAPPLES\u003C/em\u003E.",
//        "score": 155.21996,
//        "microbes": [],
//        {
//                "nutrientId": 1008,
//                "nutrientName": "Energy",
//                "nutrientNumber": "208",
//                "unitName": "KCAL",
//                "derivationCode": "LCCS",
//                "derivationDescription": "Calculated from value per serving size measure",
//                "derivationId": 70,
//                "value": 381,
//                "foodNutrientSourceId": 9,
//                "foodNutrientSourceCode": "12",
//                "foodNutrientSourceDescription": "Manufacturer's analytical; partial documentation",
//                "rank": 300,
//                "indentLevel": 1,
//                "foodNutrientId": 29879425
//                },