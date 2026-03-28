package bsu.edu.cs.user;

import bsu.edu.cs.calculators.WorkItCalc;

import java.io.*;
import java.util.*;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private double weight;
    private String weightMeasurement;
    private double height;
    private String goal;
    private int activityLevel;

    private final String filePath = "src/main/resources/UserData/UserDatabase.csv";

    public User(int id, String username, String password, String name, double weight, String weightMeasurement, double height, String goal, int activityLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.weight = weight;
        this.weightMeasurement = weightMeasurement;
        this.height = height;
        this.goal = goal;
        this.activityLevel = activityLevel;
    } // end User

    // getters for all variables
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public String getWeightMeasurement() { return weightMeasurement; }
    public double getHeight() { return height; }
    public String getGoal() { return goal; }
    public int getActivityLevel() { return activityLevel; }

    // setters for all variable (aside from Id and weightMeasurement)
    public void setUsername(String username) {
        this.username = username;
        updateDatabase();
    } // end setUsername
    public void setPassword(String password) {
        this.password = password;
        updateDatabase();
    } // end setPassword
    public void setName(String name) {
        this.name = name;
        updateDatabase();
    } // end setName
    public void setWeight(double weight) {
        this.weight = weight;
        updateDatabase();
    } // end setWeight
    public void setHeight(double height) {
        this.height = height;
        updateDatabase();
    } // end setHeight
    public void setGoal(String goal) {
        this.goal = goal;
        updateDatabase();
    } // end setGoal
    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
        updateDatabase();
    } // end setActivityLevel

    private void updateDatabase() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length > 0 && Integer.parseInt(data[0]) == this.id) {
                    line = id + "," + username + "," + password + "," + name + "," + weight + "," + weightMeasurement + "," + height + "," + goal + "," + activityLevel;
                } // end if

                lines.add(line);
            } // end while
        } catch (IOException e) {
            throw new RuntimeException("Failed to read user database", e);
        } // end try/catch

        try (FileWriter writer = new FileWriter(filePath)) {
            for (String l : lines) {
                writer.write(l + "\n");
            } // end for
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user database", e);
        } // end try/catch
    } // end updateDatabase
} // close class
