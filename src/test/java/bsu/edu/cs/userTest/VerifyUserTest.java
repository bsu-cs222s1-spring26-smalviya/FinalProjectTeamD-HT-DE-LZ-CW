package bsu.edu.cs.userTest;

import bsu.edu.cs.user.VerifyUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VerifyUserTest {
    @Test
    public void testUserExists(){
        boolean userExists = false;
        String testFile = "src/test/resources/TestUserData.CSV";
        String username = "LooBaka";
        String password = "dogs";
        VerifyUser verifyUser = new VerifyUser(testFile);
        userExists = verifyUser.verifyUserExists(username,password);
        Assertions.assertTrue(userExists);
    }
    @Test
    public void testGetUserID(){
        boolean userExists = false;
        String testFile = "src/test/resources/TestUserData.CSV";
        String username = "LooBaka";
        String password = "dogs";
        int id = -1;
        int expectedoutput = 2;
        VerifyUser verifyUser = new VerifyUser(testFile);
        if(verifyUser.verifyUserExists(username,password)){id = verifyUser.getID();}
        Assertions.assertEquals(expectedoutput,id);
    }
}
