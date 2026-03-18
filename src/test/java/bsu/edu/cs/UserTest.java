package bsu.edu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void shouldCreateUserWithUsername() {
        User user = new User("john123");

        assertEquals("john123", user.getUsername());
    } // end test

    @Test
    void shouldCreateUserWithAttributes() {
        User user = new User("John", 180, 70, "loss");

        assertEquals("John", user.getUsername());
        assertEquals(180, user.getWeight());
        assertEquals(70, user.getHeight());
        assertEquals("loss", user.getGoal());
    } // end test
} // close class
