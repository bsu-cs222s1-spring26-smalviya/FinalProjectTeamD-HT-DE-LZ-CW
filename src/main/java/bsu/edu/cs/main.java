package bsu.edu.cs;

package bsu.edu.cs;

import java.io.FileWriter;
import java.io.IOException;

public class main {

    private User user;

    public static void main(String[] args) {
        System.out.println("Hi welcome to WorkItOut, are you a new user? Y/N");
    }

    // Create new user and save to file
    public void isNewUser(String id, String password, String name,
                          int weight, int height, String goal, int calories) {
        user = new User(name, weight, height, goal, calories);
        user.setPassword(password);

        // Append to CSV
        try (FileWriter fw = new FileWriter("UserData.csv", true)) {
            fw.write("\n" + id + "," + password + "," + name + "," +
                    weight + "," + height + "," + goal + "," + calories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load existing user
    public void existingUser(String name) {
        user = new User(name);
    }

    public User getUser() {
        return user;
    }
}