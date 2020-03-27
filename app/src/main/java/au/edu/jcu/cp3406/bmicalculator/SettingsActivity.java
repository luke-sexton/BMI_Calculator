package au.edu.jcu.cp3406.bmicalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


public class SettingsActivity extends Activity {
    private static final String PREFERENCES_KEY = "preferences";
    private static final String MEASUREMENT_KEY = "measurement";
    private static final String GENDER_KEY = "gender";
    private static final String AGE_KEY = "age";
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
        setViews();

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

    private void setViews() {
        setContentView(R.layout.activity_settings);
        measurementToggle = findViewById(R.id.measurement_toggle);
        genderToggle = findViewById(R.id.gender_toggle);
        ageTextView = findViewById(R.id.age_view);
        ageBar = findViewById(R.id.age_bar);
    }

    public void doneClicked(View view) {
        // Save user's chosen settings into preferences.
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putBoolean("is_first_run", true);
        preferencesEditor.putBoolean(MEASUREMENT_KEY, measurementToggle.isChecked());
        preferencesEditor.putBoolean(GENDER_KEY, genderToggle.isChecked());
        preferencesEditor.apply();
        finish();
    }

    private void loadPreferences() {
        // Load previously saved preferences.
        String ageText = preferences.getString(AGE_KEY, AGE_PLACEHOLDER_TEXT);
        String ageBarText = ageText.replace(AGE_PLACEHOLDER_TEXT, "");

        measurementToggle.setChecked(preferences.getBoolean(MEASUREMENT_KEY, false));
        genderToggle.setChecked(preferences.getBoolean(GENDER_KEY, false));
        ageTextView.setText(ageText);

        if (!ageBarText.equals("")) {
            ageBar.setProgress(Integer.parseInt(ageBarText));
        }
    }
}
