package bsu.edu.cs.calculators;
Scanner scanner = new Scanner(System.in);

int BMR;
char genderChar;
boolean male;
int calories;
boolean none = false;
boolean light = false;
boolean moderately = false;
boolean intensely = false;
boolean five;
double calories;
public class WorkItCalc {
    public int calculateCaloricNeeds(double weight, double height, String goal) {
        return 2000; // placeholder for testing purposes
System.out.println("What is your gender? M or F?");
        gender = Scanner.nextLine();

        genderChar = gender.charAt(0);

        male == genderChar == "M";

        if (male) 
        {
            BMR =  (int) (66 + (6.23 * weight) + (12.7 * height) - (6.8 * age));
        }
        else 
        {
              BMR = (int) (665 + (4.35 * weight) + (4.7 * height) - (4.7 * age));
        }
        if (male)
              {
            System.out.println("Your BMR is " + BMR);
        }
        else
            System.out.println("Your BMR is " + BMR);
        }
    
     if (none)
        {
            calories =  (BMR * 1.2);
        }
        else if (light)
        {
            calories = (BMR * 1.375);
        }
        else if (moderately)
        {
            calories = (BMR * 1.55);
        }
        else if (intensely)
        {   
            calories = (BMR * 1.725);
        }
        else
        {
            calories = (BMR * 1.9);
        }

        System.out.println("What is your level of exercise? ");
        System.out.println("Type in none if you do not exercise. ");
        System.out.println("Type in 2 if you engage in light exercise one to three days a week.");
        System.out.println("Type in 3 if you do exercise moderately three to five times a week.");
        System.out.println("Type in 4 if you do intensely six to seven days a week.");
        System.out.println("Type in 5 if you do exercise intensely six to seven days a week and have a physically active job.");

        exercise = keyboard.nextLine();
        none = keyboard.nextLine() != null;

        System.out.println("Your daily calorie needs " + cal);
    }
}
