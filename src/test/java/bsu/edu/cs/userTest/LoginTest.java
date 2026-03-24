package bsu.edu.cs.userTest;

import bsu.edu.cs.user.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public void testSetUsername(){
        Login loginPage = new Login();
        String expectedUsername = "fishTaco";
        loginPage.setUsername("fishTaco");
        String actualUsername = loginPage.getUsername();
        Assertions.assertEquals(expectedUsername,actualUsername);
    }
    @Test
    public void testSetPassword(){
        Login loginPage = new Login();
        String expectedPassword = "fishTaco";
        loginPage.setPassword("fishTaco");
        String actualPassword = loginPage.getPassword();
        Assertions.assertEquals(expectedPassword,actualPassword);
    }
}
