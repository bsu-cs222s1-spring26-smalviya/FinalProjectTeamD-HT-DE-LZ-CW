package bsu.edu.cs.userTest;

import bsu.edu.cs.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void shouldCreateUser() {
        User user = new User(1, "JohnSmith1", "Password123", "John", 180, "LBS", 70, "LOSE", 3);

        assertEquals(1, user.getId());
        assertEquals("JohnSmith1", user.getUsername());
    } // end test
} // close class
