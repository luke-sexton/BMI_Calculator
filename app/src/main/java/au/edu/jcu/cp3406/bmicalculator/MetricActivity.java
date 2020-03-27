package au.edu.jcu.cp3406.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class MetricActivity extends Activity {
    private static final String HEIGHT_KEY = "height";
    private static final String WEIGHT_KEY = "weight";
    private int height;
    private int weight;
    private TextView heightText;
    private TextView weightText;
    SeekBar.OnSeekBarChangeListener seekBarListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric);

        if (savedInstanceState != null) {
            height = savedInstanceState.getInt(HEIGHT_KEY);
            weight = savedInstanceState.getInt(WEIGHT_KEY);
        }

        heightText = findViewById(R.id.height_view);
        weightText = findViewById(R.id.weight_view);
        setOnSeekBarChangeListener();
        setSeekBarListeners();
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

    private void setOnSeekBarChangeListener() {
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

    private void setSeekBarListeners() {
        SeekBar heightBar = findViewById(R.id.height_bar);
        SeekBar weightBar = findViewById(R.id.weight_bar);

        heightBar.setOnSeekBarChangeListener(seekBarListener);
        weightBar.setOnSeekBarChangeListener(seekBarListener);
    }

    public void calculateClicked(View view) {
        // Send intent to start Calculate activity.
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(MenuItem menuItem) {
        // Create intent to start the settings activity that expects a result.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
