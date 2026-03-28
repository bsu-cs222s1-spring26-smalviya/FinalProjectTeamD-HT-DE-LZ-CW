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

    public int getId() { return id; }
    public String getUsername() { return username; }

} // close class
