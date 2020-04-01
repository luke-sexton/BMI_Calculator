package au.edu.jcu.cp3406.bmicalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorTest {
    private static final double DELTA = 0.001;
    @Test
    public void calculateMetric() {
        // Test that the metric calculation method is correct.
        Calculator calculator = new Calculator();
        int height = 170;
        int weight = 70;
        double expectedResult = 24.2;

        double result = calculator.calculateMetric(height, weight);
        assertEquals(expectedResult, result, DELTA);
    }

    @Test
    public void calculateImperial() {
        // Test that the imperial calculation method is correct.
        Calculator calculator = new Calculator();
        int feet = 6;
        int inches = 4;
        int weight = 201;
        double expectedResult = 24.5;

        double result = calculator.calculateImperial(feet, inches, weight);
        assertEquals(expectedResult, result, DELTA);
    }
}