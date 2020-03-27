package au.edu.jcu.cp3406.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_KEY = "preferences";
    private static final String MEASUREMENT_KEY = "measurement";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start activity based on user's saved preferences.
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("is_first_run", false);

        if (!isFirstRun) {
            setContentView(R.layout.activity_main);

        } else {
            boolean measurement = preferences.getBoolean(MEASUREMENT_KEY, false);

            if (measurement) {
                intent = new Intent(this, ImperialActivity.class);
            } else {
                intent = new Intent(this, MetricActivity.class);
            }
        }
        startActivity(intent);
    }

    public void settingsClicked(View view) {
        intent = new Intent(this, SettingsActivity.class);
    }

}