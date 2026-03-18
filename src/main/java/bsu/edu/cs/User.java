package bsu.edu.cs;

public class User {
    /*public User(String username, double weight, double height, String goal) {
        WorkItCalc calc = new WorkItCalc();
        calc.calculateCaloricNeeds(weight,height,goal);
    }*/

    private final String username;

    public User(String username) {
        this.username = username;
    } // end User

    public String getUsername() {
        return username;
    } // end getUsername
}
