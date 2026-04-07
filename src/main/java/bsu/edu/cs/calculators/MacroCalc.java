package bsu.edu.cs.calculators;
import java.util.Scanner;
public class MacroCalc {
  public static void main(String[] args) {

    double tdee = bmr * 1.55;
    
    double protein = (tdee * 0.30) / 4;
    double fat = (tdee * 0.30) / 9;
    double carbs = (tdee * 0.40)/4;
      
  }
}
