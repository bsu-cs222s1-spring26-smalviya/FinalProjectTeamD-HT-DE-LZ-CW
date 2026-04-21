package bsu.edu.cs.guiTest;

import bsu.edu.cs.gui.MainApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainAppTests {
    @Test
    void testMainAppExists() {
        assertNotNull(new MainApp());
    } // end test
} // close
