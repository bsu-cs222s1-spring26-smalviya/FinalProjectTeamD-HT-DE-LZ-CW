package bsu.edu.cs;

public class User {
    /*public User(String username, double weight, double height, String goal) {
        WorkItCalc calc = new WorkItCalc();
        calc.calculateCaloricNeeds(weight,height,goal);
    }*/

    private final String username;
    private String name;
    private double weight;
    private double height;
    private String goal;

    public User(String username) {
        this.username = username;
    } // end User

    public User(String name, double weight, double height, String goal) {
        this.username = name;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
    } // end User

    public String getUsername() {
        return username;
    } // end getUsername
}
