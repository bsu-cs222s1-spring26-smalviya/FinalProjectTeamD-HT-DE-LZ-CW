package bsu.edu.cs.user;

public class Login {
    private static String username;
    private static String password;
    public void setUsername(String username) {
        Login.username = username;
    }
    public void setPassword(String password){
        Login.password = password;
    }
    public int getId(){
        VerifyUser userVerification = new VerifyUser();
        if(userVerification.verifyUserExists(username,password)){
            return userVerification.getID();
        }
        return -1;
    }
    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }
}
