package bsu.edu.cs.UserTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VerifyUserTest {
    @Test
    public void testUserExists(){
        boolean userExists = false;
        String testFile = "src/test/resources/TestUserData.CSV";
        String username = "LooBaka";
        String password = "dogs";
        VerifyUser verifyUser = new VerifyUser();
        userExists = verifyUser.verifyUserExists(username,password,testFile);
        Assertions.assertTrue(userExists);
    }
    @Test
    public void testGetUserID(){
        boolean userExists = false;
        String testFile = "src/test/resources/TestUserData.CSV";
        String username = "LooBaka";
        String password = "dogs";
        int id;
        int expectedoutput = 2;
        VerifyUser verifyUser = new VerifyUser();
        if(verifyUser.verifyUserExists(username,password,testFile)){id = verifyUser.getID();}
        Assertions.assertEquals(expectedoutput,id);
    }
}
