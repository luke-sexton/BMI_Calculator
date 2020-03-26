package au.edu.jcu.cp3406.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ImperialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imperial);
    }

    public void calculateClicked(View view) {
        // Send intent to start Calculate activity.
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }
}
