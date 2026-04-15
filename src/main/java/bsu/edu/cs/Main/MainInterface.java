package bsu.edu.cs.Main;

import bsu.edu.cs.user.User;

import javax.print.attribute.IntegerSyntax;
import java.io.ObjectInputValidation;
import java.util.Scanner;

public class MainInterface {
    protected static User user;
    protected Scanner scanner = new Scanner(System.in);

    //This will return an integer based on what the user chooses
    public int mainMenuUserRequest() {
        System.out.println("Would you like to... \n(1) open foodLogger \n(2) user settings?\n(3) quit");
        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();
            try {
                return Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Try again!");
            }
        }
        return -1;
    }
    public int getUserID(){
        return user.getId();
    }
}
