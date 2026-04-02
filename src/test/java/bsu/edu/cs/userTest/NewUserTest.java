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
        User user = new User(1000, "Username", "Password", "Name", 180, "lbs", 70, "lose", 3, "male");
        boolean result = newUser.createNewUser(user);

        assertTrue(result);
    } // end test

    @Test
    void shouldRejectDuplicateUsername() {
        NewUser newUser = new NewUser();

        User user1 = new User(2000., "duplicateUser", "pass", "Name", 180, "lbs", 70, "lose", 3, "male");
        User user2 = new User(2000., "duplicateUser", "pass", "Name", 180, "lbs", 70, "lose", 3, "male");

        newUser.createNewUser(user1);
        boolean result = newUser.createNewUser(user2);

        assertFalse(result);
    } // end test
} // close class
