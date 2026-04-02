package bsu.edu.cs.foodData;

import bsu.edu.cs.APIApps.USDAClient.USDAListParser;
import bsu.edu.cs.APIApps.USDAClient.USDAToJsonClient;
import bsu.edu.cs.foodData.DataQuery;
import bsu.edu.cs.foodData.FoodItem;
import bsu.edu.cs.foodData.LogTime;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class FoodInterface {

    private final DataQuery dataQuery;
    private final Scanner scanner;

    public FoodInterface(int userID) {
        this.dataQuery = new DataQuery(userID);
        this.scanner = new Scanner(System.in);
    }

    public void openFoodMenu() throws JSONException, IOException {
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

    private void logNewFood() throws JSONException, IOException {
        boolean logNewFoodIsRunning = true;
        while(logNewFoodIsRunning) {
            System.out.print("\nEnter the name of the food you ate: ");
            String foodName = scanner.nextLine();
            System.out.println(dataQuery.searchFood(foodName));
            System.out.println("Which food item is the one you are looking for? (1-5) type 0 to search again");
            String choice = scanner.nextLine();
            int fdcID;

            switch (choice){
                case "1","2","3","4","5":
                    fdcID = dataQuery.getFoodID(Integer.parseInt(choice));
                    FoodItem newItem = new FoodItem(fdcID);
                    LogTime timer = new LogTime();
                    String currentTime = timer.getCurrentTime();
                    timer.parseTimeStringIntoVariables(currentTime);
                    dataQuery.logFoodItem(timer.getMonth(), timer.getDay(), timer.getYear(), newItem);
                    System.out.println("Logged at: " + timer.makeTimeReadable());
                    logNewFoodIsRunning = false;
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid input. Try again!");
            }
        }
    }


    private void viewTodayLog() {
        LogTime timer = new LogTime();
        timer.parseTimeStringIntoVariables(timer.getCurrentTime());

        System.out.println("\nRetrieving log for " + timer.makeTimeReadable().substring(0, 10) + "...");

        String logData = dataQuery.grabUserLogForDay(timer.getMonth(), timer.getDay(), timer.getYear());
        System.out.println(logData);
    }
}