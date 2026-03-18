package bsu.edu.cs;

public class User {
    private final String username;
    private String name;
    private double weight;
    private double height;
    private String goal;
    private String password;

    public User(String username) {
        this.username = username;
    } // end User

    public User(String name, double weight, double height, String goal) {
        this.username = name;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
    } // end User

    public String getUsername() { return username; } // end getUsername
    public String getName() { return name; } // end getName
    public double getWeight() { return weight; } // end getWeight
    public double getHeight() { return height; } // end getHeight
    public String getGoal() { return goal; } // end getGoal

    public void setPassword(String password) {
        this.password = password;
    } // end setPassword
    public String getPassword() { return password; } // end getPassword

    public int getCaloricNeeds() {
        WorkItCalc calc = new WorkItCalc();
        return calc.calculateCaloricNeeds(weight, height, goal);
    } // end getCaloricNeeds
} // close class
