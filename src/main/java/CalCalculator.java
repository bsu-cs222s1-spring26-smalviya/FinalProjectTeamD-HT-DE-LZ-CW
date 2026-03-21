import java.util.Scanner;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;


public class CalCalculator {

    private static final Map<String, Double> Activity_Levels = Map.of(
            "Inactive", 1.2,
            "Barely Active", 1.375,
            "Moderately Active", 1.55,
            "Active", 1.725,
            "Super Active", 1.9
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int age = (int) getValidNumber(scanner, "What is your age?: ");

            System.out.print("Gender? M/F: ");
            String gender = scanner.next().toLowerCase();

            double weight = getValidNumber(scanner, "What is your weight in kg?: ");
            double height = getValidNumber(scanner, "What is your height?(In cm): ");

            scanner.nextLine();
            System.out.print("How active are you?(Inactive,Barely Active,Moderately Active,Active, Super Active): ");
            String activity = scanner.next().toLowerCase();

            System.out.print("What's your goal? (gain,lose,maintain): ");
            String goal = scanner.next().toLowerCase();

            double bmr = calculateBMR(weight, height, age, gender);

            if (Activity_Levels.containsKey(activity)) {
                double dailyCalories = bmr * Activity_Levels.get(activity);

                if (goal.equals("gain")) dailyCalories += 500;
                else if (goal.equals("lose")) dailyCalories -= 500;

                String result = String.format("\nTo %s weight, you should aim for about %d calories per day.", goal, (int) dailyCalories);
                System.out.println(result);

            } else {
                System.out.println("Invalid activty level entered.");
            }

            System.out.print("\nWant to calculate again? (yes/no): ");
            if (!scanner.next().equalsIgnoreCase("yes")) {
                System.out.println("Thanks for using the calculator! Stay healthy and fit!");
                break;
            }
        }
        scanner.close();
    }

    private static double getValidNumber(Scanner sc, String prompt) {
while (true ) {
    System.out.print(prompt);
    if (sc.hasNextDouble()) return sc.nextDouble();
    System.out.println("Invalid input. Please enter a number.");
    sc.next();
}
    }
    private static double calculateBMR(double w, double h, int a, String g) {
        if (g.equals("male")) return (10 * w) + (6.25 * h) - (5 * a) + 5;
        return (10 * w) + (6.25 * h) - (5 * a) - 161;
    }



    private static void saveToFile(String content) {
        try(FileWriter writer = new FileWriter("Caloric_needs.txt")) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

}
