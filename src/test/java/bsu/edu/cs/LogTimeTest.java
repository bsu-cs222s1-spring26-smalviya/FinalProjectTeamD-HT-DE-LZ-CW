package bsu.edu.cs;

import bsu.edu.cs.foodData.LogTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogTimeTest {

    @Test
    public void testParseTimeStringIntoVariables() {
        LogTime logTime = new LogTime();
        String testInput = "2026-03-23 18:30:15";

        logTime.parseTimeStringIntoVariables(testInput);

        assertEquals(2026, logTime.getYear(), "Year should be parsed as 2026");
        assertEquals(3, logTime.getMonth(), "Month should be parsed as 3");
        assertEquals(23, logTime.getDay(), "Day should be parsed as 23");
        assertEquals(18, logTime.getHour(), "Hour should be parsed as 18");
        assertEquals(30, logTime.getMinutes(), "Minutes should be parsed as 30");
        assertEquals(15, logTime.getSeconds(), "Seconds should be parsed as 15");
    }

    @Test
    public void testMakeTimeReadable() {
        LogTime logTime = new LogTime();
        String testInput = "2026-03-23 09:05:00";

        logTime.parseTimeStringIntoVariables(testInput);

        String expectedOutput = "03/23/2026 09:05:00";
        assertEquals(expectedOutput, logTime.makeTimeReadable(), "Readable time string is not formatted correctly");
    }

    @Test
    public void testGetCurrentTimeFormat() {
        LogTime logTime = new LogTime();

        String currentTime = logTime.getCurrentTime();

        assertNotNull(currentTime, "Current time string should not be null");
        assertEquals(19, currentTime.length(), "Current time string should match the 19-character ISO format");
    }
}