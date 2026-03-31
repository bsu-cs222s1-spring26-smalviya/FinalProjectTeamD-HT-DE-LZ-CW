package bsu.edu.cs.CalculatorTests.empty;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class txt {
    @Test
    public void testing(){
        String usdaAPIKey = System.getProperty("usdaKey","DEMO_KEY");
        System.out.println(usdaAPIKey);
        Assertions.assertTrue(true);
    }
}
