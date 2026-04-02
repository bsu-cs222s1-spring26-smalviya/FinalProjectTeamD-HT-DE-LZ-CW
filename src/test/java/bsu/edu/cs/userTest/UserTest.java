package bsu.edu.cs.userTest;

import bsu.edu.cs.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void shouldCreateUser() {
        User user = new User(1, "JohnSmith1", "Password123", "John", 180, "lbs", 70, "lose", 3,  "male");

        assertEquals(1, user.getId());
        assertEquals("JohnSmith1", user.getUsername());
    } // end test

    @Test
    void shouldUpdateWeight() {
        User user = new User(1, "u", "p", "n", 0, "lbs", 0, "lose", 1, "male");

        user.setWeight(5);

        assertEquals(5, user.getWeight());
    } // end test

    @Test
    void shouldUpdateGoal() {
        User user = new User(1, "u", "p", "n", 0, "lbs", 0, "lose", 1, "male");

        user.setGoal("gain");

        assertEquals("gain", user.getGoal());
    } // end test

    @Test
    void shouldNotCrashWhenUpdatingDatabase() {
        User user = new User(1, "u", "p", "n", 0, "lbs", 0, "lose", 1, "male");

        assertDoesNotThrow(() -> user.setWeight(190));
    } // end test
} // close class
