package bsu.edu.cs.calculators;
public class MacroCalc {
private double conversionRatio;

  public void calcByWeight(double foodItemWight, double userInputedFoodWeight) {
    if (userInputedFoodWeight != 0) {
      this.conversionRatio = foodItemWeight / userInputedFoodWeight;
    } else {
      this.conversionRatio = 0;
    }
  }
  public void calcByPortion (double userInputedFoodPortion) {
    
  }
  public double getConversionRatio() {
    return conversionRatio;
  }
}
