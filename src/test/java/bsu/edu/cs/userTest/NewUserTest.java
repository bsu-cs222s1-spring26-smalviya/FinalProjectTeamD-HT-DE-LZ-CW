package bsu.edu.cs.userTest;

import bsu.edu.cs.user.NewUser;
import bsu.edu.cs.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NewUserTest {
    @Test
    void shouldCreateNewUserObject() {
        NewUser newUser = new NewUser();

        assertNotNull(newUser);
    } // end test

    @Test
    void shouldCreateNewUserSuccessfully() {
        NewUser newUser = new NewUser();
        User user = new User(1000, "Username", "Password", "Name", 180, "LBS", 70, "LOSE", 3);
        boolean result = newUser.createNewUser(user);

        assertTrue(result);
    } // end test
} // close class
