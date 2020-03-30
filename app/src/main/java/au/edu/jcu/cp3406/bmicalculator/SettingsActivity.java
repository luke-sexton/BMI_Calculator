package au.edu.jcu.cp3406.bmicalculator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class SettingsActivity extends Activity {
    public static final int SETTINGS_REQUEST = 1;
    private static final String GENDER = "Gender";
    private static final String AGE = "Age";
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

        // Create preferences to store/retrieve user's settings in memory.
        preferences = getSharedPreferences(MainActivity.PREFERENCES, MODE_PRIVATE);
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
        System.out.println("doneClicked");
        // Save preferences and finish activity.
        savePreferences();
        displayPreferenceToast();
        setResult(RESULT_OK);
        finish();
    }

    private void displayPreferenceToast() {
        System.out.println("displayPreferenceToast");
        // Display message to user that their settings are saved.
        Context context = getApplicationContext();
        CharSequence preferencesSaved = "Preferences Saved.";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, preferencesSaved, duration).show();
    }

    private void loadPreferences() {
        System.out.println("loadPreferences");
        // Load previously saved preferences.
        String ageText = preferences.getString(AGE, AGE_PLACEHOLDER_TEXT);
        genderToggle.setChecked(preferences.getBoolean(GENDER, false));
        measurementToggle.setChecked(preferences.getBoolean(MainActivity.MEASUREMENT, false));

        // Set Age TextView and SeekBar Progress.
        ageTextView.setText(ageText);

        ageText = ageText.replace(AGE_PLACEHOLDER_TEXT, "");
        if (!ageText.equals("")) {
            ageBar.setProgress(Integer.parseInt(ageText));
        }

    }

    private void savePreferences() {
        System.out.println("savePreferences");
        // Save user's chosen settings into preferences.
        SharedPreferences.Editor preferencesEditor = preferences.edit();

        preferencesEditor.putBoolean(MainActivity.INITIAL_STATE, false);
        preferencesEditor.putBoolean(MainActivity.MEASUREMENT, measurementToggle.isChecked());
        preferencesEditor.putBoolean(GENDER, genderToggle.isChecked());
        preferencesEditor.putString(AGE, ageTextView.getText().toString().replace(AGE_PLACEHOLDER_TEXT, ""));

        preferencesEditor.apply();
    }

    private void setViews() {
        System.out.println("setViews");
        // Set content and object views.
        setContentView(R.layout.activity_settings);
        measurementToggle = findViewById(R.id.measurement_toggle);
        genderToggle = findViewById(R.id.gender_toggle);
        ageTextView = findViewById(R.id.age_view);
        ageBar = findViewById(R.id.age_bar);
    }
}

