package bsu.edu.cs.CalculatorTests;

import bsu.edu.cs.calculators.MacroCalc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.Mac;

public class MacroCalcTest {
    @Test
    public void calcByWeightTest(){
        double weightOfUserFood = 140;//grams
        double weightOfServingSize = 200; //grams
        MacroCalc macroCalc = new MacroCalc();
        double expectedRatio = 140/200;
        MacroCalc.calcByWeight(weightOfUserFood,weightOfServingSize)
        double actualRatio = macroCalc.getRatio();
        Assertions.assertEquals(expectedRatio,actualRatio);
    }
    @Test
    public void calcByPortion(){
        double portionSize = 1.75;
        MacroCalc macroCalc = new MacroCalc();
        double expectedRatio = 1.75;
        macroCalc.calcByPortion(portionSize);
        double actualRatio = macroCalc.getRatio();
        Assertions.assertEquals(expectedRatio,actualRatio);
    }

    @Test
    public void calcByPortionTwice(){
        double portionSize = 1.75;
        double portionSizeAgain = 3.0;
        MacroCalc macroCalc = new MacroCalc();
        macroCalc.calcByPortion(portionSize);
        System.out.println(macroCalc.getRatio());
    }
}
