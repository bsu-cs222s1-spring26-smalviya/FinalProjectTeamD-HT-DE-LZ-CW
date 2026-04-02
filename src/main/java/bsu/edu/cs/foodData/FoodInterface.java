package bsu.edu.cs.foodData;

import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.foodData.FoodItem;
import bsu.edu.cs.foodData.LogTime;

import java.util.Scanner;

public class FoodInterface {

    private DataQuery dataQuery;
    private Scanner scanner;

    public FoodInterface() {
        this.dataQuery = new DataQuery();
        this.scanner = new Scanner(System.in);
    }

    public void openFoodMenu() {
        boolean running = true;

        while (running) {
            System.out.println("1. Log a new Food Item");
            System.out.println("2. View Today's Food Log");
            System.out.println("3. Search for Recipes");
            System.out.println("4. Return to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    logNewFood();
                    break;
                case "2":
                    viewTodayLog();
                    break;
                case "3":
                    System.out.println("\n[Recipe Search is currently under construction.]");
                    break;
                case "4":
                    System.out.println("Returning...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private void logNewFood() {
        System.out.print("\nEnter the name of the food you ate: ");
        String foodName = scanner.nextLine();

        FoodItem newItem = new FoodItem(foodName);

        LogTime timer = new LogTime();
        String currentTime = timer.getCurrentTime();
        timer.parseTimeStringIntoVariables(currentTime);

        dataQuery.logFoodItem(timer.getMonth(), timer.getDay(), timer.getYear(), newItem);

        System.out.println("Logged at: " + timer.makeTimeReadable());
    }

    private void viewTodayLog() {
        LogTime timer = new LogTime();
        timer.parseTimeStringIntoVariables(timer.getCurrentTime());

        System.out.println("\nRetrieving log for " + timer.makeTimeReadable().substring(0, 10) + "...");

        String logData = dataQuery.grabUserLogForDay(timer.getMonth(), timer.getDay(), timer.getYear());
        System.out.println(logData);
    }
}