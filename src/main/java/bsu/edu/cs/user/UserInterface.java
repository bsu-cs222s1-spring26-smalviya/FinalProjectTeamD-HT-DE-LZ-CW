package bsu.edu.cs.user;

import java.util.Random;
import java.util.Scanner;

public class UserInterface {

    private int userID;
    private final Scanner scanner = new Scanner(System.in);
    public int getUserID(){
        return userID;
    }

    public void openSettingsMenu(){
        User currentUser = new User(userID);
        boolean accessingSettings = true;
        while(accessingSettings){
            System.out.println("What would you like to change?:" +
                    "\n(1) Name" +
                    "\n(2) Username" +
                    "\n(3) Password" +
                    "\n(4) Weight" +
                    "\n(5) Height" +
                    "\n(6) Goal" +
                    "\n(7) Activity Level" +
                    "\n(8) Exit settings");
            String userResponse = scanner.nextLine();
            switch (userResponse) {
                case "1":
                    System.out.println("Type in your new name:");
                    String newName = scanner.nextLine();
                    currentUser.setName(newName);
                    break;
                case "2":
                    System.out.println("Type in your new username:");
                    String newUsername = scanner.nextLine();
                    currentUser.setUsername(newUsername);
                    break;
                case "3":
                    System.out.println("Type in your new password:");
                    String newPassword = scanner.nextLine();
                    currentUser.setPassword(newPassword);
                    break;
                case "4":
                    System.out.println("Type in your new weight in kg:");
                    double newWeight = Double.parseDouble(scanner.nextLine());
                    currentUser.setWeight(newWeight);
                    break;
                case "5":
                    System.out.println("Type in your new height:");
                    double newHeight = Double.parseDouble(scanner.nextLine());
                    currentUser.setHeight(newHeight);
                    break;
                case "6":
                    String newGoal = getGoal();
                    currentUser.setGoal(newGoal);
                    break;
                case "7":
                    int newActivityLevel = getActivityLevel();
                    currentUser.setActivityLevel(newActivityLevel);
                    break;
                case "8":
                    accessingSettings = false;
                    break;
                default:
                    System.out.println("Sorry, please try again!");
            }
        }
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
                        System.out.println("Sorry, username or password is incorrect press enter to try again, or 'n'" +
                                " to return to menu!");
                        String userReturningResponse = scanner.nextLine();
                        if (userReturningResponse.toLowerCase().contains("n")){
                            accessingResponse = false;
                            break;
                        }
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