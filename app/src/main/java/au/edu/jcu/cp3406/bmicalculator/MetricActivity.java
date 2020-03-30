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
    private static final String HEIGHT_KEY = "height";
    private static final String WEIGHT_KEY = "weight";
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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Save user values to instance state.
        super.onSaveInstanceState(outState);
        outState.putInt(HEIGHT_KEY, height);
        outState.putInt(WEIGHT_KEY, weight);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Start preferred activity from
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("MetricActivity onActivityResult");

        if (resultCode == RESULT_OK) {
            setResult(resultCode);
            finish();
        }
    }

    private void setViews() {
        setContentView(R.layout.activity_metric);
        heightBar = findViewById(R.id.height_bar);
        weightBar = findViewById(R.id.weight_bar);
        heightText = findViewById(R.id.height_view);
        weightText = findViewById(R.id.weight_view);
    }

    public void calculateClicked(View view) {
        // Send intent to start Calculate activity.

        // put data into intent, finish() to go back to main activity
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void settingsIconClicked(MenuItem item) {
        // Create intent to start the settings activity that expects a result.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }
}
