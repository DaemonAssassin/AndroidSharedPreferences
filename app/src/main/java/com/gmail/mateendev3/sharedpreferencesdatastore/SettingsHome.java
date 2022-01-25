package com.gmail.mateendev3.sharedpreferencesdatastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gmail.mateendev3.sharedpreferencesdatastore.model.Student;
import com.google.gson.Gson;

public class SettingsHome extends AppCompatActivity {

    //declaring member
    private SharedPreferences.OnSharedPreferenceChangeListener mPreferenceChangeListener;

    TextView tvGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_home);

        TextView tv = findViewById(R.id.tv_setting_home);
        tvGson = findViewById(R.id.tv_gson);
        Button btnSettings = findViewById(R.id.btn_preference_activity);
        btnSettings.setOnClickListener(this::onSettingsActivityOpen);




        //getting instance of default preference file
        SharedPreferences defaultPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //initializing shared preference changed listener
        mPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("username_key"))
                    tv.setText(sharedPreferences.getString("username_key", ""));
            }
        };

        //registering listener to defaultPreferences
        defaultPreferences.registerOnSharedPreferenceChangeListener(mPreferenceChangeListener);

        //setting value to gson
        setValueToGsonTV();
    }

    private void setValueToGsonTV() {
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String string = preferences.getString(LoginActivity.GSON_KEY, "");
        Student student = gson.fromJson(string, Student.class);
        tvGson.setText(student.toString());
    }

    private void onSettingsActivityOpen(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}