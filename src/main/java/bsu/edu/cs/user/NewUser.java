package bsu.edu.cs.user;

import java.io.*;

public class NewUser {

    public boolean createNewUser(User user) {
        saveToDatabase(user);
        createUserLog(user);
        return true;
    } // end createNewUser

    private void saveToDatabase(User user) {
        try (FileWriter writer = new FileWriter("src/main/resources/UserData/UserDatabase.csv", true)) {
            writer.write(System.lineSeparator() + user.toCSVLine());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user database", e);
        } // end try/catch
    } // end saveToDatabase

    private void createUserLog(User user) {
        try (FileWriter writer = new FileWriter("src/main/resources/UserData/logs/" + user.getId() + ".csv")) {
            writer.write("month,day,year,itemName,calories,potassium,iron,fat,protein,calcium,sugar,fiber,carbs,cholesterol,weight,measurement\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user log", e);
        } // end try/catch
    } // end createUserLog
} // close class
