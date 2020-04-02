package au.edu.jcu.cp3406.bmicalculator;

class Calculator {
    private static final int TO_INCHES = 12;
    private static final double TO_METRES = 100;

    Calculator() {
    }

    double calculateMetric(int height, int weight) {
        // Metric BMI Formula = weight (kg) / [height (m)]^2

        // Convert height to metres.
        double calculationHeight = height / TO_METRES;

        // Return metric BMI formula calculation rounded to 1 decimal point.
        double calculation = weight / (Math.pow(calculationHeight, 2));
        return Math.round(calculation * 10.0) / 10.0;
    }

    double calculateImperial(int feet, int inches, int weight) {
        // Imperial BMI Formula = 703 x weight (lb) / [height (in)]^2

        // Convert feet to inches
        feet *= TO_INCHES;
        double total_height = feet + inches;

        // Return imperial BMI formula calculation rounded to 1 decimal point.
        double calculation = 703 * (weight / Math.pow(total_height, 2));
        return Math.round(calculation * 10.0) / 10.0;
    }
}
