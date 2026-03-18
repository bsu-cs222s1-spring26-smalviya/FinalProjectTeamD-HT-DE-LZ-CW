package bsu.edu.cs;

public class User {
    public User(String username, double weight, double height, String goal) {
        WorkItCalc calc = new WorkItCalc();
        calc.calculateCaloricNeeds(weight,height,goal);
    }

    public void setPassword(String password) {
    }

    public void setUsername(String username) {
    }

    public int getCaloricNeeds() {
    }

    public String getUsername() {
    }
}
