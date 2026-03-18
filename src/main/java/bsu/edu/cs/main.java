package bsu.edu.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {

    static private User user;
    static private final Scanner scanner = new Scanner(System.in);
    static private DataQuery dataQuery = new DataQuery();
    public static void main(String[] args) {
        System.out.println("Hi welcome to WorkItOut, type in your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        user = dataQuery.findUser(username,password);
        if (user==null){
            isNewUser(username,password);
        }
    }

    // Create new user and save to file
    private static void isNewUser(String username,String password) {
        System.out.println("Since your username or password don't exist, please enter the info below");
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your weight:");
        double weight = scanner.nextDouble();
        System.out.println("Enter your height in centimeters");
        double height = scanner.nextDouble();
        System.out.println("Enter your goal (loss,gain,maintain)");
        String goal = scanner.nextLine();
        user = new User(name, weight, height, goal);
        user.setUsername(username);
        user.setPassword(password);
        String userID = user.getUsername();
        int calories = user.getCaloricNeeds();
        // Append to CSV
        try (FileWriter fw = new FileWriter("UserData.csv", true)) {
            fw.write("\n" + userID + "," + password + "," + name + "," +
                    weight + "," + height + "," + goal + "," + calories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}