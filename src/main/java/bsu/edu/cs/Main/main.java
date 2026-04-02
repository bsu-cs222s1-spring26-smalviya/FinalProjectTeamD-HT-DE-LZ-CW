package bsu.edu.cs.Main;

import bsu.edu.cs.foodData.FoodInterface;
import bsu.edu.cs.user.User;
import bsu.edu.cs.user.UserInterface;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class main {

    static private User user;
    static private final Scanner scanner = new Scanner(System.in);
    static private final UserInterface userInterface = new UserInterface();
    static private FoodInterface foodInterface;

    public void main(String[] args){
        userInterface.logInScreen();
        user = new User(userInterface.getUserID());
        foodInterface = new FoodInterface(userInterface.getUserID());
        try {
            runProgram();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Would you like to open foodLogger or user settings? (1 or 2)");
    }

    private static void runProgram() throws JSONException, IOException {
        boolean running = true;
        while(running){
            System.out.println("Would you like to... \n(1) open foodLogger \n(2) user settings?\n(3) quit");
            String response = scanner.nextLine();
            switch (response){
                case "1":
                    foodInterface.openFoodMenu();
                    break;
                case "2":
//                    userInterface.openSettingsMenu();
                    System.out.println("Currently Unavailable");
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Sorry, invalid input. Try Again!");
            }
        }
    }
}