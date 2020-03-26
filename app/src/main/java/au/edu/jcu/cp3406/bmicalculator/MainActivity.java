package au.edu.jcu.cp3406.bmicalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_KEY = "preferences";
    private static final String MEASUREMENT_KEY = "measurement";
    private static final String MEASUREMENT_DEFAULT = "Metric";
    private static final String MEASUREMENT_IMPERIAL = "Imperial";
    private String measurementValue = MEASUREMENT_DEFAULT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* If no saved instance, load settings activity to set user's preferences.
           Otherwise set user's saved measurement preference stored in memory. */
        if (savedInstanceState == null) {
            startSettingsActivity();
        } else {
            SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
            measurementValue = preferences.getString(MEASUREMENT_KEY, MEASUREMENT_DEFAULT);
        }

        startChosenActivity();
    }

    private void startChosenActivity() {
        // Sends intent to start new activity based on user's chosen measurement.
        switch (measurementValue) {
            case MEASUREMENT_DEFAULT:
                Intent metricIntent = new Intent(this, MetricActivity.class);
                startActivity(metricIntent);
                break;
            case MEASUREMENT_IMPERIAL:
                Intent imperialIntent = new Intent(this, ImperialActivity.class);
                startActivity(imperialIntent);
        }
    }

    private void startSettingsActivity() {
        // Create intent to start the settings activity that expects a result.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    public void calculateClicked(View view) {
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Set layout depending on user's measurement setting.
        super.onActivityResult(requestCode, resultCode, data);


        // Results from SettingsActivity intent
        if (requestCode == SettingsActivity.SETTINGS_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                measurementValue = data.getStringExtra(MEASUREMENT_KEY);
            }
        }

        // Results from Calculate
    }
}