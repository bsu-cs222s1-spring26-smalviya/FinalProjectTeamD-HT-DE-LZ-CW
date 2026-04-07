package bsu.edu.cs.user;

import java.util.Random;
import java.util.Scanner;

public class UserInterface {

//    public void openSettingsMenu(){
//        System.out.println("What would you like to change?:" +
//                "(1) Username\n" +
//                "(2) Password\n");
//    }
    private int userID;
    private final Scanner scanner = new Scanner(System.in);
    public int getUserID(){
        return userID;
    }

    public void logInScreen(){
        boolean accessingResponse = true;
        while(accessingResponse){
            System.out.println("Would you like to:" +
                    "\n(1) Log in" +
                    "\n(2) Sign up");
            String userResponse = scanner.nextLine();
            switch (userResponse) {
                case "1":
                    Login login = new Login();
                    System.out.println("Type in your username:");
                    String username = scanner.nextLine();
                    System.out.println("Type in your password:");
                    String password = scanner.nextLine();
                    login.setUsername(username);
                    login.setPassword(password);
                    if(login.getId() == -1){
                        System.out.println("Sorry, username or password is incorrect, try again!");
                    }else{
                        System.out.println("Logging in...");
                        userID = login.getId();
                        accessingResponse = false;
                    }
                    break;
                case "2":
                    int randomId = (int)(Math.random() * 10000);
                    userID = randomId;
                    System.out.println("Type in your name:");
                    String name = scanner.nextLine();
                    System.out.println("Type in your desired username");
                    String userName = scanner.nextLine();
                    System.out.println("Type in your desired password");
                    String newPassword = scanner.nextLine();
                    System.out.println("Type in your weight in kg");
                    double weight = Double.parseDouble(scanner.nextLine());
                    String weightMeasurement = "kg";
                    System.out.println("Type in your height");
                    double height = Double.parseDouble(scanner.nextLine());

                    String goal = getGoal();

                    int activityLevel = getActivityLevel();

                    String gender = getGender();

                    User user = new User(userID,userName,newPassword,name,weight,weightMeasurement,height,goal,activityLevel,gender);
                    NewUser newUser = new NewUser();
                    newUser.createNewUser(user);
                    break;
                default:
                    System.out.println("Sorry, please try again!");
            }
        }
    }

    private String getGoal() {
        boolean responding = true;
        String goal = "";
        while(responding) {
            System.out.println("Are you here to " +
                    "\n(1) Lose weight" +
                    "\n(2) Gain weight" +
                    "\n(3) Maintain weight");
            String userGoalChoice = scanner.nextLine();
            switch (userGoalChoice){
                case "1":
                    goal = "Loss";
                    responding = false;
                    break;
                case "2":
                    goal = "Gain";
                    responding = false;
                    break;
                case "3":
                    goal = "Maintain";
                    responding = false;
                    break;
                default:
                    System.out.println("Not an option, try again.");
            }
        }
        return goal;
    }

    private int getActivityLevel() {
        int activityLevel = 0;
        boolean findingActivityLevel = true;
        while(findingActivityLevel){
            System.out.println("From 1-5, how active are you?");
            String userActivityLevelChoice = scanner.nextLine();
            switch (userActivityLevelChoice){
                case "1":
                    activityLevel = 1;
                    findingActivityLevel = false;
                    break;
                case "2":
                    activityLevel = 2;
                    findingActivityLevel = false;
                    break;
                case "3":
                    activityLevel = 3;
                    findingActivityLevel = false;
                    break;

                case "4":
                    activityLevel = 4;
                    findingActivityLevel = false;
                    break;

                case "5":
                    activityLevel = 5;
                    findingActivityLevel = false;
                    break;

                default:
                    System.out.println("Not an option, try again.");
            }
        }
        return activityLevel;
    }

    private String getGender() {
        String gender = "";
        boolean findingGender = true;
        while(findingGender){
            System.out.println("Which of these were you assigned at birth?" +
                    "\n(1) Male" +
                    "\n(2) Female");
            String userChoice = scanner.nextLine();
            switch (userChoice){
                case "1":
                    gender = "male";
                    findingGender = false;
                    break;
                case "2":
                    gender = "female";
                    findingGender = false;
                    break;
                default:
                    System.out.println("Please try again!");
            }
        }
        return gender;
    }
}
