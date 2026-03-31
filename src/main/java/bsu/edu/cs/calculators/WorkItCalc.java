package bsu.edu.cs.calculators;

public class WorkItCalc {
    public int CalculateCaloricNeeds(double weight, double height,int age, String goal, int activityLevel,String gender){
        double caloricNeeds = 0;
        switch (gender){
            case "male":
                caloricNeeds = (10*weight)+(6.25*height)-(5*age) + 5;
                break;
            case "female":
                caloricNeeds = (10*weight)+(6.25*height)-(5*age) - 161;
                break;
        }
        switch (goal){
            case "loss":
                caloricNeeds = caloricNeeds - ((double) 5 /activityLevel)*30;
                break;
            case "gain":
                caloricNeeds = caloricNeeds + (activityLevel)*100;
            case "maintain":
                caloricNeeds = caloricNeeds;
        }
        return (int)caloricNeeds;
    }
}
