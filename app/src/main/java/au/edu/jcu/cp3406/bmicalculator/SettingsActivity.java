package au.edu.jcu.cp3406.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


public class SettingsActivity extends Activity {
    public static final int SETTINGS_REQUEST = 1;
    private static final String MEASUREMENT_KEY = "measurement";
    private static final String GENDER_KEY = "gender";
    private static final String AGE_KEY = "age";
    private static final String PREFERENCES_KEY = "preferences";
    private static final String AGE_PLACEHOLDER_TEXT = "AGE: ";
    private SharedPreferences preferences;
    private ToggleButton measurementToggle;
    private ToggleButton genderToggle;
    private SeekBar ageBar;
    private TextView ageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view, set view objects with reference id and load shared preferences.
        setContentView(R.layout.activity_settings);
        measurementToggle = findViewById(R.id.measurement_toggle);
        genderToggle = findViewById(R.id.gender_toggle);
        ageTextView = findViewById(R.id.age_view);
        ageBar = findViewById(R.id.age_bar);

        // Create preferences to store user's settings in memory.
        preferences = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
        loadPreferences();

        ageBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String displayText = AGE_PLACEHOLDER_TEXT + progress;
                ageTextView.setText(displayText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void doneClicked(View view) {
        Intent intent = new Intent();

        if (measurementToggle.isChecked()) {
            // Measurement is imperial
            intent.putExtra(MEASUREMENT_KEY, measurementToggle.getText().toString());
        } else {
            // Measurement is Metric
            intent.putExtra(MEASUREMENT_KEY, measurementToggle.getText().toString());
        }

        setResult(RESULT_OK, intent);
        savePreferences();
        finish();
    }

    private void loadPreferences() {
        // Load previously saved preferences.
        boolean measurementIsChecked = preferences.getBoolean(MEASUREMENT_KEY, false);
        boolean genderIsChecked = preferences.getBoolean(GENDER_KEY, false);
        String ageText = preferences.getString(AGE_KEY, AGE_PLACEHOLDER_TEXT);

        measurementToggle.setChecked(measurementIsChecked);
        genderToggle.setChecked(genderIsChecked);
        ageTextView.setText(ageText);
        ageBar.setProgress(Integer.parseInt(ageText.replace(AGE_PLACEHOLDER_TEXT, "")));
    }


    public void savePreferences() {
        // Save user's chosen settings into preferences.
        SharedPreferences.Editor preferencesEditor = preferences.edit();

        preferencesEditor.putBoolean(MEASUREMENT_KEY, measurementToggle.isChecked());
        preferencesEditor.putBoolean(GENDER_KEY, genderToggle.isChecked());
        preferencesEditor.putString(AGE_KEY, ageTextView.getText().toString());
        preferencesEditor.apply();
    }
}
