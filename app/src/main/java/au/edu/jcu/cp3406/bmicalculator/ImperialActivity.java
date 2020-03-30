package au.edu.jcu.cp3406.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ImperialActivity extends AppCompatActivity {
    public static final int IMPERIAL_REQUEST = 3;
    private static final String FEET_KEY = "feet";
    private static final String INCHES_KEY = "inches";
    private static final String WEIGHT_KEY = "weight";
    private int feet;
    private int inches;
    private int weight;
    private TextView feetView;
    private TextView inchesView;
    private TextView weightView;
    private SeekBar feetBar;
    private SeekBar inchesBar;
    private SeekBar weightBar;
    SeekBar.OnSeekBarChangeListener seekBarListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view, set view objects with reference id.
        setViews();

        // Set height and weight values from saved instance state.
        if (savedInstanceState != null) {
            feet = savedInstanceState.getInt(FEET_KEY);
            inches = savedInstanceState.getInt(INCHES_KEY);
            weight = savedInstanceState.getInt(WEIGHT_KEY);
        }

        // Create listener that will action changes made to seek bars.
        createOnSeekBarChangeListener();

        // Set listener to seek bars.
        feetBar.setOnSeekBarChangeListener(seekBarListener);
        inchesBar.setOnSeekBarChangeListener(seekBarListener);
        weightBar.setOnSeekBarChangeListener(seekBarListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("ImperialActivity onActivityResult");

        // Confirm finished settings activity.
        if (resultCode == RESULT_OK) {
            // Finish imperial activity and return to main activity.
            setResult(resultCode);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Save user values to instance state.
        super.onSaveInstanceState(outState);
        outState.putInt(FEET_KEY, feet);
        outState.putInt(INCHES_KEY, inches);
        outState.putInt(WEIGHT_KEY, weight);
    }

    public void calculateClicked(View view) {
        // Create intent to start calculate activity.
        Intent intent = new Intent(this, CalculateActivity.class);

        // Add data to intent.
        intent.putExtra(FEET_KEY, feet);
        intent.putExtra(INCHES_KEY, inches);
        intent.putExtra(WEIGHT_KEY, weight);
        startActivity(intent);
    }

    private void createOnSeekBarChangeListener() {
        // Get text from each TextView
        final String feetViewText = feetView.getText().toString();
        final String inchesViewText = inchesView.getText().toString();
        final String weightViewText = weightView.getText().toString();

        seekBarListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String feetDisplayText = feetViewText + ": " + progress;
                String inchesDisplayText = inchesViewText + ": " + progress;
                String weightDisplayText = weightViewText + ": " + progress;
                // Text field will show the integer from the seek bar's position.
                switch (seekBar.getId()) {
                    case R.id.height_feet_seekBar:
                        feet = progress;
                        feetView.setText(feetDisplayText);
                        break;
                    case R.id.height_inches_seekBar:
                        inches = progress;
                        inchesView.setText(inchesDisplayText);
                    case R.id.weight_bar:
                        weight = progress;
                        weightView.setText(weightDisplayText);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };
    }

    public void settingsIconClicked(MenuItem menuItem) {
        System.out.println("Imperial Activity settingsIconClicked");
        // Create intent to start the settings activity that expects a result.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    private void setViews() {
        // Set XML layout to content view.
        setContentView(R.layout.activity_imperial);

        // TextViews
        feetView = findViewById(R.id.height_feet);
        inchesView = findViewById(R.id.height_inches);
        weightView = findViewById(R.id.weight_imperial);

        // SeekBars
        feetBar = findViewById(R.id.height_feet_seekBar);
        inchesBar = findViewById(R.id.height_inches_seekBar);
        weightBar = findViewById(R.id.weight_imperial_seekBar);
    }
}
