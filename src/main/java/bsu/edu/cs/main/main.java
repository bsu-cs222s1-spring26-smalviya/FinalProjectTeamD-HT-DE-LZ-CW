package bsu.edu.cs.main;

import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.user.User;

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
        if (user==null){
            isNewUser(username,password);
        }
        runLogger();
    }
/*
    // Create new user and save to file
    //This will go into another class called interface later on.
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
        try (FileWriter fw = new FileWriter("UserData/UserDatabase.csv", true)) {
            fw.write("\n" + userID + "," + password + "," + name + "," +
                    weight + "," + height + "," + goal + "," + calories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void runLogger(){
        boolean running = true;
        while(running) {
            //More options to come later like recipe search and all that
            System.out.print(mainMenu());
            int userResponse = scanner.nextInt();
            switch (userResponse){
                case 0:
                    System.out.println("quitting program....");
                    running = false;
                    break;
                case 1:
                    //Later we will make it so the user can input the item and we will find the foods
                    System.out.println(dataQuery.displayFoodData("rice"));
                    break;
                case 2:
                    dataQuery.uploadDataToFiles();
                    System.out.println("Food logged...");
                    break;
                case 3:
                    System.out.println("Here is your food data for today: ");
                    //Later we will make it so it shows the days that the user has logged and they can choose
                    //For testing cases tho this will be the date we choose.
                    dataQuery.grabUserLogForDay(12,07,25);
                    break;
            }
        }
    }
    private static String mainMenu(){
        return "\nHere are your options for the logger: \n" +
                "Type any of the numbers below\n" +
                "1. Get Food info\n" +
                "2. Log Food\n" +
                "3. Get user food log for today\n" +
                "Input number here (or type 0 to quit): ";
    }
*/}