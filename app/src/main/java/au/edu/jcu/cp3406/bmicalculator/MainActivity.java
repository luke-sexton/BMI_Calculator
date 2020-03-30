package au.edu.jcu.cp3406.bmicalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static final String PREFERENCES = "Preferences";
    static final String MEASUREMENT = "Measurement";
    static final String INITIAL_STATE = "Initial State";
    private SharedPreferences preferences;
    public Intent intent;
    public Intent settingsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

        settingsIntent = new Intent(this, SettingsActivity.class);

        // Start activity based on user's saved preferences.
        preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean isInitialState = preferences.getBoolean(INITIAL_STATE, true);

        if (isInitialState) {
            setContentView(R.layout.activity_main);
        } else {
            startPreferredActivity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Start preferred activity from
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("MainActivity onActivityResult");

        if (resultCode == RESULT_OK) {
            startPreferredActivity();
        }
    }

    public void startPreferredActivity() {
        System.out.println("startPreferredActivity");
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
        System.out.println("startSettingsActivity");
        // Start settings activity.
        intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(settingsIntent, SettingsActivity.SETTINGS_REQUEST);
    }

}