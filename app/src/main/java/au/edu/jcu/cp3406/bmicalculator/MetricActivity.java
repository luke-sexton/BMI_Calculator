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


public class MetricActivity extends AppCompatActivity {
    public static final int METRIC_REQUEST = 2;
    private static final String HEIGHT_KEY = "Height";
    private static final String WEIGHT_KEY = "Weight";
    private static final String CALCULATION_KEY = "Calculation";
    private int height;
    private int weight;
    private TextView heightText;
    private TextView weightText;
    private SeekBar heightBar;
    private SeekBar weightBar;
    SeekBar.OnSeekBarChangeListener seekBarListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view, set view objects with reference id.
        setViews();

        // Set height and weight values from saved instance state.
        if (savedInstanceState != null) {
            height = savedInstanceState.getInt(HEIGHT_KEY);
            weight = savedInstanceState.getInt(WEIGHT_KEY);
        }

        // Create listener that will action changes made to seek bars.
        createOnSeekBarChangeListener();

        // Set listener to seek bars.
        heightBar.setOnSeekBarChangeListener(seekBarListener);
        weightBar.setOnSeekBarChangeListener(seekBarListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Confirm finished SettingsActivity to finish this activity and return to MainActivity.
        super.onActivityResult(requestCode, resultCode, data);

        // Confirm finished settings activity.
        if (resultCode == RESULT_OK) {
            // Finish metric activity and return to main activity.
            setResult(resultCode);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create options menu for settings icon.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    private void createOnSeekBarChangeListener() {
        // Text field will show the integer from the seek bar's position.
        final String heightViewText = heightText.getText().toString();
        final String weightViewText = weightText.getText().toString();

        seekBarListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String heightDisplayText = heightViewText + ": " + progress;
                String weightDisplayText = weightViewText + ": " + progress;
                // Text field will show the integer from the seek bar's position.
                switch (seekBar.getId()) {
                    case R.id.height_bar:
                        height = progress;
                        heightText.setText(heightDisplayText);
                        break;
                    case R.id.weight_bar:
                        weight = progress;
                        weightText.setText(weightDisplayText);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Save user values to instance state.
        super.onSaveInstanceState(outState);
        outState.putInt(HEIGHT_KEY, height);
        outState.putInt(WEIGHT_KEY, weight);
    }

    public void calculateClicked(View view) {
        // Create intent for results activity.
        Intent intent = new Intent(this, ResultsActivity.class);

        // Calculate BMI with user's height and weight.
        Calculator calculator = new Calculator();
        double calculation = calculator.calculateMetric(height, weight);

        // Add user's calculated BMI to intent.
        intent.putExtra(CALCULATION_KEY, calculation);

        // Start results activity.
        startActivity(intent);
    }

    public void settingsIconClicked(MenuItem item) {
        // Create intent to start the settings activity that expects a result.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    private void setViews() {
        // Set XML layout to content view.
        setContentView(R.layout.activity_metric);

        // TextViews
        heightText = findViewById(R.id.height_view);
        weightText = findViewById(R.id.weight_view);

        // SeekBars
        heightBar = findViewById(R.id.height_bar);
        weightBar = findViewById(R.id.weight_bar);
    }
}
