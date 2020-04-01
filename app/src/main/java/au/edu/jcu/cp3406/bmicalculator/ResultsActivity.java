package au.edu.jcu.cp3406.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    private static final String CALCULATION_KEY = "Calculation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content and text views with reference id.
        setContentView(R.layout.activity_results);
        TextView resultsView = findViewById(R.id.results);

        // Get user's information from intent.
        Intent intent = getIntent();
        double calculatedBMI = intent.getDoubleExtra(CALCULATION_KEY, 0.0);

        // Display user's BMI on results TextView.
        String resultText = "Your BMI Results: " + calculatedBMI;
        resultsView.setText(resultText);
    }

    public void okayClicked(View view) {
        // Finish and return to previous activity.
        finish();
    }
}
