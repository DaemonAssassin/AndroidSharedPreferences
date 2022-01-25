package com.gmail.mateendev3.sharedpreferencesdatastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private static final String COLOR_KEY = "color.key";
    SwitchCompat switchCompat;
    ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        switchCompat = findViewById(R.id.switch_color);
        rootLayout = findViewById(R.id.root_layout);
        Button button = findViewById(R.id.btn_sign_out);
        button.setText(getIntent().getStringExtra(LoginActivity.EMAIL_KEY));


        //App based preference / custom preference
        //calling getSharedPreference
        SharedPreferences.Editor editor = getSharedPreferences(LoginActivity.FILE_NAME, MODE_PRIVATE).edit();
        editor.putString(LoginActivity.EMAIL_KEY, getIntent().getStringExtra(LoginActivity.EMAIL_KEY));
        editor.apply();
        findViewById(R.id.btn_sign_out)
                .setOnClickListener(v -> {
                    finish();
                });



        //getting value form activity based preference
        SharedPreferences activityPreferences = getPreferences(MODE_PRIVATE);
        boolean hasChecked = activityPreferences.getBoolean(COLOR_KEY, false);
        applyBackground(hasChecked);
        switchCompat.setChecked(hasChecked);
        //default preferences set / activity based preference
        //Checking if switch state changes and adding listener to it
        //getPreference method
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor defaultEditor = getPreferences(MODE_PRIVATE).edit();
            defaultEditor.putBoolean(COLOR_KEY, isChecked);
            defaultEditor.apply();
            applyBackground(isChecked);
        });
    }

    private void applyBackground(boolean isChecked) {
        rootLayout.setBackgroundColor(isChecked ? Color.DKGRAY : Color.WHITE);
    }
}