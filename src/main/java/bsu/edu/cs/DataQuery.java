package bsu.edu.cs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataQuery {
    private FoodItem item;
    //These will be hard coded to be the test ones for now;
    private String personalUserCSVLog = "src/test/resources/TestHumbertoLogData.csv";
    private String UserDatabase = "src/test/resources/TestUserData.CSV";

    //Maybe it would be better to link this stuff to the user or something
    
    //I want this to display the data from a specific item that the user is looking at,
    //I have the item variable stored here locally so if we need to, we can call different things without
    //having to mention the item as a parameter every time
    public String displayFoodData(String foodName) {
        item = new FoodItem(foodName);
        return "Calories: " + (int)item.getCalories() + " kCal\n" +
                "Protein: " + item.getProtein() + " grams\n" +
                "Carbs: " + item.getCarbs() + " grams";
    }
    //This will be better in the user class but for now works here because noone else wrote a function
    //for this to be possible.
    public User findUser(String id, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(UserDatabase))) {
            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].equals(id) && data[1].equals(password)) {
                    return new User(
                            data[2],
                            Integer.parseInt(data[3]),
                            Integer.parseInt(data[4]),
                            data[5]
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Grabs the user log of food for the day mentioned and then returns a string of it.
    public String grabUserLogForDay(int month, int day, int year) {

        try (BufferedReader br = new BufferedReader(new FileReader(personalUserCSVLog))) {
            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int m = Integer.parseInt(data[0]);
                int d = Integer.parseInt(data[1]);
                int y = Integer.parseInt(data[2]);

                if (m == month && d == day && y == year) {

                    this.item = new FoodItem(data[3]);

                    return capitalize(data[3]) + ",\n " +
                            data[4] + " calories,\n" +
                            data[5] + " postassium,\n" +
                            data[6] + " iron,\n" +
                            data[7] + " fat,\n" +
                            data[8] + " protein,\n" +
                            data[9] + " calcium,\n" +
                            data[10] + " sugar,\n" +
                            data[11] + " fiber,\n" +
                            data[12] + " carbs,\n" +
                            data[13] + " cholesterol";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Still have to finish this one
    public void uploadDataToFiles() {
        // not implemented yet
    }

    private String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
