package bsu.edu.cs.calculators;
public class MacroCalc {
private double conversionRatio;

  public void calcByWeight(double foodItemWeight, double userInputedFoodWeight) {
    if (userInputedFoodWeight != 0) {
      this.conversionRatio = userInputedFoodWeight / foodItemWeight;
    } else {
      this.conversionRatio = 0;
    }
  }
  public void calcByPortion (double userInputedFoodPortion) {
    //how was this not even finished tho
  }
  public double getConversionRatio() {
    return conversionRatio;
  }
}
