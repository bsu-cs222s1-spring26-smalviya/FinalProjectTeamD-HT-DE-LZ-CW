package bsu.edu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void shouldCreateUserWithUsername() {
        User user = new User("john123");

        assertEquals("john123", user.getUsername());
    } // end test
} // close class
