package bsu.edu.cs.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerifyUser {

    private static String userDatabase;
    private int userID;
    public VerifyUser(){
        userDatabase = "src/main/resources/UserData/UserDatabase.csv";
    }
    public VerifyUser(String testFile) {
        userDatabase = testFile;
    }

    public boolean verifyUserExists(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(userDatabase))) {
            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int idOnFile = Integer.parseInt(data[0]);
                String usernameOnFile = data[1];
                String passwordOnFile = data[2];

                if (usernameOnFile.equals(username)&&passwordOnFile.equals(password)) {
                    userID = Integer.parseInt(data[0]);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getID(){
        return userID;
    }
}
