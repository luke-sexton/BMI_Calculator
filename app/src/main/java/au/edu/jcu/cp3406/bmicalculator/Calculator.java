package au.edu.jcu.cp3406.bmicalculator;

public class Calculator {
    private static final int FEET_TO_INCHES = 12;
    private static final int CENTIMETRES_TO_METRES = 100;

    private double calculation;

    public Calculator() {}

    public double calculateMetric(int height, int weight) {
        // Metric BMI Formula = weight (kg) / [height (m)]^2

        // Convert height to metres.
        height /= CENTIMETRES_TO_METRES;

        // Return metric BMI formula calculation.
        return calculation = weight / Math.pow(height, 2);
    }

    public double calculateImperial(int feet, int inches, int weight) {
        // Imperial BMI Formula = 703 x weight (lb) / [height (in)]^2

        // Convert feet to inches
        feet *= FEET_TO_INCHES;
        double total_height = feet + inches;

        // Return imperial BMI formula calculation
        return calculation = 703 * (weight / Math.pow(total_height, 2));
    }
}
