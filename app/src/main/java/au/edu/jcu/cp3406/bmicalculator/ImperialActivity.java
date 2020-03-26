package au.edu.jcu.cp3406.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;


public class ImperialActivity extends Activity {
    private static final String FEET_KEY = "feet";
    private static final String INCHES_KEY = "inches";
    private static final String WEIGHT_KEY = "weight";
    private int feet;
    private int inches;
    private int weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imperial);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Save user values to instance state.
        super.onSaveInstanceState(outState);
        outState.putInt(FEET_KEY, feet);
        outState.putInt(INCHES_KEY, inches);
        outState.putInt(WEIGHT_KEY, weight);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void calculateClicked(View view) {
        // Send intent to start Calculate activity.
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(MenuItem menuItem) {
        // Create intent to start the settings activity that expects a result.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }
}
