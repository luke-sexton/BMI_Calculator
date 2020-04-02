package au.edu.jcu.cp3406.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String PREFERENCES = "Preferences";
    static final String MEASUREMENT = "Measurement";
    static final String INITIAL_STATE = "Initial State";
    private SharedPreferences preferences;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start activity based on user's saved preferences.
        preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean isInitialState = preferences.getBoolean(INITIAL_STATE, true);

        // TESTING - Clear preferences for testing purposes.
        preferences.edit().clear().apply();

        /* If there are no saved preferences, display welcome layout,
           otherwise start preferred activity based off saved measurement. */
        if (isInitialState) {
            setContentView(R.layout.activity_main);
        } else {
            startPreferredActivity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Ensure activities finished correctly before starting the preferred activity.
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            startPreferredActivity();
        }
    }

    public void startPreferredActivity() {
        // Start activity from user's chosen measurement.
        boolean measurement = preferences.getBoolean(MEASUREMENT, false);

        if (measurement) {
            intent = new Intent(this, ImperialActivity.class);
            startActivityForResult(intent, ImperialActivity.IMPERIAL_REQUEST);
        } else {
            intent = new Intent(this, MetricActivity.class);
            startActivityForResult(intent, MetricActivity.METRIC_REQUEST);
        }
    }

    public void startSettingsActivity(View view) {
        // Start settings activity.
        intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }
}