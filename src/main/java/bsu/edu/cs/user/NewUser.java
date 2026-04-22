package bsu.edu.cs.user;

import java.io.*;

public class NewUser {

    private final String filePath = "src/main/resources/UserData/UserDatabase.csv";

    public boolean createNewUser(User user) {
        if (usernameExists(user.getUsername())) {
            return false;
        } // end if

        saveToDatabase(user);
        createUserLog(user);
        return true;
    } // end createNewUser

    private boolean usernameExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            String line;
            while((line = br.readLine()) != null) {
                if (line.split(",")[1].equals(username)) {
                    return true;
                } // end if
            } // end while
        } catch (IOException e) {
            throw new RuntimeException(e);
        } // end try/catch
        return false;
    } // end usernameExists

    private void saveToDatabase(User user) {
        try (FileWriter writer = new FileWriter("src/main/resources/UserData/UserDatabase.csv", true)) {
            writer.write(System.lineSeparator() + user.toCSVLine());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user database", e);
        } // end try/catch
    } // end saveToDatabase

    private void createUserLog(User user) {
        try (FileWriter writer = new FileWriter("src/main/resources/UserData/logs/" + user.getId() + "LogData.csv")) {
            writer.write("month,day,year,time,name,fdcID,calories,potassium,iron,unsatFat,satFat,protein,calcium,sugar,fiber,carbs,cholesterol,weight,measurement\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user log", e);
        } // end try/catch
    } // end createUserLog

    private int getNextUserId(String filePath) {
        int maxId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length > 0) {
                    try {
                        int id = Integer.parseInt(data[0]);

                        if (id > maxId) {
                            maxId = id;
                        } // end if
                    } catch (NumberFormatException e) {
                        // Skip header or bad rows
                    } // end try/catch
                } // end if
            } // end while
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user database", e);
        } // end try/catch
        return maxId;
    } // end getNextUserId
} // close class
