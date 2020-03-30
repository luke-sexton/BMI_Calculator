package au.edu.jcu.cp3406.bmicalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class ImperialActivity extends AppCompatActivity {
    public static final int IMPERIAL_REQUEST = 3;
    private static final String FEET_KEY = "feet";
    private static final String INCHES_KEY = "inches";
    private static final String WEIGHT_KEY = "weight";
    private TextView feetView;
    private TextView inchesView;
    private TextView weightView;
    private int feet;
    private int inches;
    private int weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imperial);

        if (savedInstanceState != null) {
            feet = savedInstanceState.getInt(FEET_KEY);
            inches = savedInstanceState.getInt(INCHES_KEY);
            weight = savedInstanceState.getInt(WEIGHT_KEY);
        }


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Start preferred activity from
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("ImperialActivity onActivityResult");

        if (resultCode == RESULT_OK) {
            setResult(resultCode);
            finish();
        }
    }

    public void calculateClicked(View view) {
        // Send intent to start Calculate activity.
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void settingsIconClicked(MenuItem menuItem) {

        // Create intent to start the settings activity that expects a result.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }
}
