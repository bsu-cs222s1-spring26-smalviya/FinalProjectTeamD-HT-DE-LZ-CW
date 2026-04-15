package bsu.edu.cs.Main;

import bsu.edu.cs.foodData.FoodInterface;
import bsu.edu.cs.user.User;
import bsu.edu.cs.user.UserInterface;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class main {
    static private final MainInterface mainInterface = new MainInterface();
    static private final UserInterface userInterface = new UserInterface();
    static private FoodInterface foodInterface;

    public static void main(String[] args) {
        logInUser();
        //user = new User(userInterface.getUserID());
        //foodInterface = new FoodInterface(userInterface.getUserID(),user.getWeight(), user.getWeightMeasurement());
        runProgram();
        //System.out.println("Would you like to open foodLogger or user settings? (1 or 2)");
    }

    private static void runProgram(){
        try {
            boolean running = true;
            while(running){
                switch (mainInterface.mainMenuUserRequest()){
                    case 1:
                        foodInterface.openFoodMenu();
                        break;
                    case 2:
                        userInterface.openSettingsMenu();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Sorry, invalid input. Try Again!");
                }
            }
        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void logInUser(){
        boolean isUserLoggedIn = false;
        while(!isUserLoggedIn) {
            userInterface.logInScreen();
            if(userInterface.getUserID() >= 0){
                isUserLoggedIn = true;
            }
        }
        foodInterface = new FoodInterface();
    }
}