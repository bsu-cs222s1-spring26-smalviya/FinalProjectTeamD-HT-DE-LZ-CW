package bsu.edu.cs.user;

import bsu.edu.cs.calculators.WorkItCalc;

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
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setHeight(double height) { this.height = height; }
    public void setGoal(String goal) { this.goal = goal; }
    public void setActivityLevel(int activityLevel) { this.activityLevel = activityLevel; }

} // close class
