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

    @Test
    void shouldUpdateWeight() {
        User user = new User(1, "u", "p", "n", 0, "LBS", 0, "LOSE", 1);

        user.setWeight(5);

        assertEquals(5, user.getWeight());
    } // end test

    @Test
    void shouldUpdateGoal() {
        User user = new User(1, "u", "p", "n", 0, "LBS", 0, "LOSE", 1);

        user.setGoal("GAIN");

        assertEquals("GAIN", user.getGoal());
    } // end test

    @Test
    void shouldNotCrashWhenUpdatingDatabase() {
        User user = new User(1, "u", "p", "n", 0, "LBS", 0, "LOSE", 1);

        assertDoesNotThrow(() -> user.setWeight(190));
    } // end test
} // close class
